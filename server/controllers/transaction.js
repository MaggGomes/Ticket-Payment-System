const
	Op = Sequelize.Op,
	request = require('request'),
	rsa = require('node-rsa'),
	bcrypt = require('bcrypt'),
	uuidv4 = require('uuid/v4'),
	User = require('../models/index').User,
	Ticket = require('../models/index').Ticket,
	Voucher = require('../models/index').Voucher,
	Transaction = require('../models/index').Transaction;

module.exports = {
	list(req, res) {
	    var id = req.params.id;
		console.log(id);
		User
			.findOne({
				where: {
					id: id.toString()
				}
			})
			.then(user => {
				if (!user) {
					return res.status(400).json({success: false, message: 'User id doesn\'t exist'});
				}
				var ticketTransactions = [];
				var orderTransactions = [];
				var validTickets = [];
				var validVouchers = [];
				Transaction
					.findAll({
						where: {
							userId: user.id
						}
					})
					.then(transactions => {
						for (let i = 0; i < transactions.length; i++) {
							if (transactions[i].orderId == null) {
								ticketTransactions.push(transactions[i]);
							} else {
								orderTransactions.push(transactions[i]);
							}
						}
						let whereClauseVoucher = {[Op.and]: [{userId: user.id}, {available: true}]};
						Voucher
							.findAll({
								where: whereClauseVoucher
							})
							.then(vouchers => {
								validVouchers = vouchers;

								let whereClauseTicket = {[Op.and]: [{userId: user.id}, {used: false}]};
								Ticket
									.findAll({
										where: whereClauseTicket
									})
									.then(tickets => {
										validTickets = tickets;
										res.status(200).json({success:true, message:'Valid',
											ticketTransactions: ticketTransactions, orderTransactions: orderTransactions,
											validTickets: validTickets, validVouchers: validVouchers});
									})
									.catch(err => {
										res.status(400).json({success: false, message: 'Error ocurred: ' + err});
									});;
							})
							.catch(err => {
								res.status(400).json({success: false, message: 'Error ocurred: ' + err});
							});;
					})
					.catch(err => {
						res.status(400).json({success: false, message: 'Error ocurred: ' + err});
					});
			});
	}
};
