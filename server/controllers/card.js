const
	Op = Sequelize.Op,
	request = require('request'),
	Card = require('../models/index').Card;

module.exports = {
	list(req, res) {
		Card
			.findAll()
			.then(cards => {
				res.status(200).json(cards);
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	}
};