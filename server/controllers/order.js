const
    Op = Sequelize.Op,
    request = require('request'),
    Order = require('../models/index').Order;

module.exports = {
    list(req, res) {
        Order
            .findAll()
            .then(orders => {
                res.status(200).json(orders);
            })
            .catch(err => {
                console.log(err);
                res.status(500).json({success: false, message: 'Error occured: ' + err});
            });
    }
};