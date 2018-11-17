const
	Op = Sequelize.Op,
	request = require('request'),
	uuidv4 = require('uuid/v4'),
	Ticket = require('../models/index').Ticket,
	Show = require('../models/index').Show,
	Voucher = require('../models/index').Voucher,
	Promotion = require('../models/index').Promotion,
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
	create(req, res){
		Show
			.findOne({
				where: {
					id:  req.decoded.message.id
				}
			})
			.then(show => {
				if(show){
					var quantity = req.decoded.message.quantity;
					var tickets = [];
					var vouchers = [];
					var promotions = [];
					for(let i = 0 ; i < quantity; i++){
						var tempSeat = 0;
						Ticket
							.findAll({
								where : {
									showId : show.id
								},
								attributes : ['seatNumber']
							})
							.then(seatNumbers => {
								tempSeat = getRandomInt(50);
								while(seatNumbers.includes(tempSeat)){
									tempSeat = getRandomInt(50);
								}
								Ticket
									.create({
										id: uuidv4(),
										seatNumber : tempSeat,
										showId: show.id,
										showName: show.name,
										showDate: show.date,
										used: false,
										userId: req.decoded.message.userId
									})
									.then(ticket => {
										tickets.push(ticket);
										let voucherId = uuidv4();
										let productId = getRandomInt(2);
										Voucher
											.create({
												id : voucherId,
												available: true,
												productId : productId,
												userId : req.decoded.message.userId
											})
											.then(voucher => {
												vouchers.push(voucher);
												res.status(200).json({
													success: true,
													tickets: tickets,
													vouchers: vouchers
												});
											});
									});
							})
							.catch(err => {
								console.log(err);
								res.status(500).json({success: false, message: 'Error occured: ' + err});
							});
					}
				} else {
					res.status(400).json({success: false, message: 'Show doesn\'t exist'});
				}
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	},
	present: function (req, res) {
		if (req.body.quantity <= 0 || req.body.quantity > 4)
			res.status(400).json({success: false, message: 'Invalid number of tickets'});

		var whereClause = {[Op.and]: [{id: req.bod.ids}, {userId : req.body.userId}]};
		Ticket
			.findAll({
				where: whereClause
			})
			.then(tickets => {
			    if(tickets.length != req.body.quantity) {
					res.status(400).json({success: false, message: 'A ticket id wasn\'t valid or didnt\' belong to User'});
				}
				var invalidTicketsId = [];
				for (let i = 0; i < req.body.quantity; i++) {
					if (tickets.used === true) {
						invalidTicketsId.push(tickets[i].id);
					} else {
						Ticket
							.update({
								used: true
							})
							.catch(err => {
								res.status(400).json({success: false, message: 'Error updating used column' + err});
							});
					}
				}
				if (invalidTicketsId.length == 0) {
					res.status(200).json({success: true, message: 'All tickets valid'});
				} else {
					if (invalidTicketsId.length == req.body.quantity) {
						res.status(200).json({
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