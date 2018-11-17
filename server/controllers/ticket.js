const
	Op = Sequelize.Op,
	request = require('request'),
	uuidv4 = require('uuid/v4'),
	Ticket = require('../models/index').Ticket,
	Show = require('../models/index').Show,
	Voucher = require('../models/index').Voucher,
	Promotion = require('../models/index').Promotion,
	Transaction = require('../models/index').Transaction,
	userController = require('../controllers/user');

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
		Show
			.findOne({
				where: {
					id: req.decoded.message.id
				}
			})
			.then(show => {
				if (show) {
					var spentBefore = 0;
					console.log('merdando aqui');
					Transaction
						.findAll({
							where : {
								userId: req.decoded.message.userId
							},
							attributes: ['price']
						})
						.then(trans => {
							for(let i = 0; i < trans.length; i++){
								spentBefore += trans[i].price;
							}
							var quantity = req.decoded.message.quantity;
							var tickets = [];
							var vouchers = [];
							var promotions = [];
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
									userId: req.decoded.message.userId
								});
								transactionBulk.push({
									userId : req.decoded.message.userId,
									ticketId: ticketId,
									price : show.price
								});

								let productId = getRandomInt(2) + 1;
								voucherBulk.push({
									id: uuidv4(),
									available: true,
									productId: productId,
									userId: req.decoded.message.userId
								});
							}

							Ticket
								.bulkCreate(ticketBulk)
								.then(()=>{
									Transaction
										.bulkCreate(transactionBulk)
										.then(()=>{
											var newSpent = 0;
											Transaction
												.findAll({
													where : {
														userId : req.decoded.message.userId
													},
													attributes : ['price']
												})
												.then(trans1 => {
													for(let i = 0; i < trans1.length; i++){
														newSpent += trans1[i].price;
													}

													let discountCalc = Math.floor((newSpent/100)) - Math.floor((spentBefore/100));
													console.log('NUMBER OF VOUCHER 5%: ' + discountCalc);
													for(let i = 0; i < discountCalc; i++){
														voucherBulk.push({
															id: uuidv4(),
															available: true,
															productId: 0,
															userId: req.decoded.message.userId
														});
													}
													Voucher
														.bulkCreate(voucherBulk)
														.then(() => {
															res.status(200).json({success: true, message: 'All created'});
														})
														.catch(err => {
															res.status(400).json({success: false, message: 'Error: ' + err});
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
	},
	verify: function (req, res) {
		if (req.body.quantity <= 0 || req.body.quantity > 4)
			res.status(400).json({success: false, message: 'Invalid number of tickets'});
		console.log(req.body.ids);
		var whereClause = {[Op.and]: [{id: {[Op.in] : req.body.ids}} , {userId : req.body.userId}]};
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
	}
};

function getRandomInt(max) {
	return Math.floor(Math.random() * Math.floor(max));
}