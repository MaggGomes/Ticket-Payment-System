const
    Op = Sequelize.Op,
    request = require('request'),
    bcrypt = require('bcrypt'),
    User = require('../models/index').User;

module.exports = {
    list(req, res) {
        User
            .findAll()
            .then(users => {
                res.status(200).json(users);
            })
            .catch(err => {
                console.log(err);
                res.status(500).json({success: false, message: 'Error occured: ' + err});
            });
    }
}

