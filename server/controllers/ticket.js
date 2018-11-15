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
										userId: req.body.userId //change to .decoded after verify works
									})
									.then(ticket => {
										tickets.push(ticket);
										let voucherId = uuidv4();
										Voucher
											.create({
												id : voucherId,
												userId : req.body.userId,//change to .decoded after verify works
												available: true
											})
											.then(voucher => {
												vouchers.push(voucher);
												const productId = getRandomInt(2); // 0: coffee, 1: popcorn
												Promotion
													.create({
														voucherId : voucherId,
														productId : productId,
														discount : 1
													})
													.then(promotion =>{
														promotions.push(promotion);
													});
											});
									});
							})
							.catch(err => {
								console.log(err);
								res.status(500).json({success: false, message: 'Error occured: ' + err});
							});
					}
					res.status(200).json({
						success: true,
						tickets: tickets,
						promotions: promotions,
						vouchers: vouchers
					});
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