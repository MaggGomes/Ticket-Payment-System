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
		//check quantity sent
		Show
			.findOne({
				where: {
					id:  req.body.message.id//change to .decoded after verify works
				}
			})
			.then(show => {
				if(show){
					var quantity = req.body.message.quantity;//change to .decoded after verify works
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
										userId: req.body.message.userId //change to .decoded after verify works
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
												userId : req.body.message.userId//change to .decoded after verify works
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
	}
};

function getRandomInt(max) {
	return Math.floor(Math.random() * Math.floor(max));
}