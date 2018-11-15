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
	},
	retrieve(req, res){
		Show
			.findOne({
				where: {
					id: req.params.id
				},
			})
			.then(show => {
				if (show)
					res.status(200).json(show);
				else {
					res.status(400).json({
						success: false,
						message: 'Show doesn\'t exist!'
					});
				}
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	}
};

