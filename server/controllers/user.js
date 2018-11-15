const
	Op = Sequelize.Op,
	request = require('request'),
	rsa = require('node-rsa'),
	bcrypt = require('bcrypt'),
	uuidv4 = require('uuid/v4'),
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
	},
	create(req, res) {
		var whereClause = {	[Op.or]: [{username: req.body.username}, {nif: req.body.nif}] };
		User
			.findOne({
				where: whereClause
			})
			.then(user => {
				if(user){
					res.status(400).json({
						success: false,
						message: 'Username/NIF already registered!'
					});
				} else {
					var hash = bcrypt.hashSync(req.body.password, 10);
					var id = uuidv4();
					User
						.create({
							id: id,
							username : req.body.username,
							password: hash,
							nif: req.body.nif,
							keyN: req.body.keyN,
							keyE: req.body.keyE
						});
					res.status(200).json({success:true, id: id}); //send id
				}
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	},
	authenticate(req, res) {
		User
			.findOne({
				where: {
					[Op.or]: [{username: req.body.username}, {nif: req.body.nif}]
				}
			})
			.then(user => {
				if (bcrypt.compareSync(req.body.password, user.password)) {
					res.status(200).json({success:true});
				}
			})
			.catch(err => {
				console.log(err);
				res.status(500).json({success: false, message: 'Error occurred: ' + err});
			});
	},
	retrieveProtectedInfo(req,res, next){
		User
			.findOne({
				where: {
					id: req.body.userId
				}
			})
			.then(user => {
				if(user){
					var key = rsa();
					key.importKey({
						n: Buffer.from(user.keyN, 'hex'),
						e: parseInt(user.keyE, 16)
					},'components-public');
					console.log(user.keyN);
					console.log(user.keyE);
					console.log(req.body.signature);
					if(key.verify(req.body.message, req.body.signature, 'utf-8', 'base64')){
						req.decoded = req.body;
						next();
					}
					else return res.status(400).json({
						success:false, message:'Invalid Signature'
					});
				} else {
					return res.json.status(400)({
						success:false, message:'User doesn\'t exist'
					});
				}
			})
			.catch(err => {
				return res.status(500).json({success: false, message: 'Error occurred: ' + err});
			});
	}
};

