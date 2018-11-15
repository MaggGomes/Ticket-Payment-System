const
	Op = Sequelize.Op,
	request = require('request'),
	Voucher = require('../models/index').Voucher;

module.exports = {
	list(req, res) {
		Voucher
			.findAll()
			.then(vouchers => {
				res.status(200).json(vouchers);
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	}
};