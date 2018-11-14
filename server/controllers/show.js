const
	Op = Sequelize.Op,
	request = require('request'),
	Show = require('../models/index').Show;

module.exports = {
	list(req, res) {
		Show
			.findAll()
			.then(shows => {
				res.status(200).json(shows);
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	}
};

