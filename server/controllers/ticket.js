const
	Op = Sequelize.Op,
	request = require('request'),
	uuidv4 = require('uuid/v4'),
	Ticket = require('../models/index').Ticket,
	Show = require('../models/index').Show,
	Voucher = require('../models/index').Voucher,
	TicketTransaction = require('../models/index').TicketTransaction,
	OrderTransaction = require('../models/index').OrderTransaction,
	ProductOrder = require('../models/index').ProductOrder,
	User = require('../models/index').User;

module.exports = {
	list(req, res) {
		Ticket
			.findAll()
			.then(tickets => {
				res.status(200).json(tickets);
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	},
	create: function (req, res) {
		User
			.findOne({
				where : {
					id: req.decoded.message.userId
				}
			})
			.then(user=>{
				if(!user){
					return res.status(400).json({success:false, message:'User id doesn\'t exist'});
				}
				Show
					.findOne({
						where: {
							id: req.decoded.message.id
						}
					})
					.then(show => {
						if (show) {
							var spentBefore = 0;
							TicketTransaction
								.findAll({
									where : {
										userId: user.id
									}
								})
								.then(trans => {
									for(let i = 0; i < trans.length; i++){
										spentBefore += trans[i].totalPrice;
									}
									OrderTransaction
										.findAll({
											where: {
												userId : user.id
											},
											attributes: ['id']
										})
										.then(otrans=>{
											var ids = [];
											for(let i = 0; i < otrans.length; i++){
												ids.push(otrans[i].id);
											}
											ProductOrder
												.findAll({
													where: {
														orderId : {[Op.in] : ids}
													}
												})
												.then(orders=>{
													for(let i = 0; i < orders.length; i++){
														spentBefore += orders[i].totalPrice;
													}
													console.log('SPENT BEFORE: ' + spentBefore);
													var quantity = req.decoded.message.quantity;
													var ticketBulk = [];
													var voucherBulk = [];
													var transactionBulk = [];
													for (let i = 0; i < quantity; i++) {
														var tempSeat = getRandomInt(100);
														var ticketId = uuidv4();
														ticketBulk.push({
															id: ticketId,
															seatNumber: tempSeat,
															showId: show.id,
															showName: show.name,
															showDate: show.date,
															used: false,
															userId: user.id
														});
														let productId = getRandomInt(2) + 1;
														voucherBulk.push({
															id: uuidv4(),
															available: true,
															productId: productId,
															userId: user.id
														});
													}
													transactionBulk.push({
														userId : user.id,
														showId : show.id,
														showName: show.name,
														showDescription : show.description,
														noTickets : quantity,
														date: Date.now(),
														price : show.price,
														totalPrice : show.price * quantity,
													});

													Ticket
														.bulkCreate(ticketBulk)
														.then(tickets=>{
															TicketTransaction
																.bulkCreate(transactionBulk)
																.then(()=>{
																	var newSpent = spentBefore + (show.price * quantity);
																	console.log('SPENT NEW: ' + newSpent);
																	let discountCalc = Math.floor((newSpent/100)) - Math.floor((spentBefore/100));
																	for(let i = 0; i < discountCalc; i++){
																		voucherBulk.push({
																			id: uuidv4(),
																			available: true,
																			productId: 0,
																			userId: user.id
																		});
																	}
																	Voucher
																		.bulkCreate(voucherBulk)
																		.then(vouchers => {
																			res.status(200).json({success: true, message: 'All created', tickets: tickets, vouchers: vouchers});
																		})
																		.catch(err => {
																			res.status(400).json({success: false, message: 'Error: ' + err});
																		});
																});
														});
												});
										});
								});
						} else {
							res.status(400).json({success: false, message: 'Show doesn\'t exist'});
						}
					})
					.catch(err => {
						console.log(err);
						res.status(500).json({success: false, message: 'Error occured: ' + err});
					});
			})
			.catch(err=>{
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	},
	validate: function (req, res) {
		User
			.findOne({
				where : {
					id: req.body.message.userId
				}
			})
			.then(user=>{
				if (req.body.quantity <= 0 || req.body.quantity > 4)
					res.status(400).json({success: false, message: 'Invalid number of tickets'});
				console.log(req.body.ids);
				var whereClause = {[Op.and]: [{id: {[Op.in] : req.body.ids}} , {userId : user.id}]};
				Ticket
					.findAll({
						where: whereClause
					})
					.then(tickets => {
						console.log(tickets);
						if(tickets.length !== req.body.quantity) {
							return res.status(400).json({success: false, message: 'A ticket id wasnt valid or didnt belong to User'});
						}
						var invalidTicketsId = [];
						var validTicketsId = [];
						for (let i = 0; i < req.body.quantity; i++) {
							if (tickets[i].used === true) {
								invalidTicketsId.push(tickets[i].id);
							} else {
								validTicketsId.push(tickets[i].id);
							}
						}
						Ticket
							.update(
								{
									used: true
								},
								{
									where: {
										id: {[Op.in]: validTicketsId}
									}
								})
							.catch(err => {
								res.status(400).json({success: false, message: 'Error updating used column' + err});
							});
						if (invalidTicketsId.length == 0) {
							res.status(200).json({success: true, message: 'All tickets valid', invalidTickets: invalidTicketsId});
						} else {
							if (invalidTicketsId.length == req.body.quantity) {
								res.status(400).json({
									success: false,
									message: 'No tickets are valid',
									invalidTickets: invalidTicketsId
								});
							} else
								res.status(200).json({
									success: false,
									message: 'Some tickets are not valid',
									invalidTickets: invalidTicketsId
								});
						}
					});
			})
			.catch(err=>{
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});;
	}
};

function getRandomInt(max) {
	return Math.floor(Math.random() * Math.floor(max));
}

/*TicketTransaction
                                                                        .findAll({
                                                                            where : {
                                                                                userId : user.id
                                                                            }
                                                                        })
                                                                        .then(trans1 => {
                                                                            for(let i = 0; i < trans1.length; i++){
                                                                                newSpent += trans1[i].totalPrice;
                                                                            }

                                                                            OrderTransaction
                                                                                .findAll({
                                                                                    where: {
                                                                                        userId : user.id
                                                                                    },
                                                                                    attributes: ['id']
                                                                                })
                                                                                .then(otrans=>{
                                                                                    var ids = [];
                                                                                    for(let i = 0; i < otrans.length; i++){
                                                                                        ids.push(otrans[i].id);
                                                                                    }
                                                                                    ProductOrder
                                                                                        .findAll({
                                                                                            where: {
                                                                                                orderId : {[Op.in] : ids}
                                                                                            }
                                                                                        })
                                                                                        .then(orders=>{
                                                                                            for(let i = 0; i < orders.length; i++){
                                                                                                spentBefore += orders[i].totalPrice;
                                                                                            }

                                                                                            console.log('SPENT NEW: ' + spentBefore);
                                                                                            */