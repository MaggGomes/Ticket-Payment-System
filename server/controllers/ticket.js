const
	Op = Sequelize.Op,
	request = require('request'),
	Ticket = require('../models/index').Ticket;

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
	}
};