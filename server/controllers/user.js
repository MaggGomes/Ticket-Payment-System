const
	Op = Sequelize.Op,
	request = require('request'),
	rsa = require('node-rsa'),
	bcrypt = require('bcrypt'),
	uuidv4 = require('uuid/v4'),
	User = require('../models/index').User,
	Card = require('../models/index').Card;

module.exports = {
	list(req, res) {
		User
			.findAll()
			.then(users => {
				res.status(200).json(users);
			})
			.catch(err => {
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	},
	create(req, res) {
		var whereClause = {	[Op.or]: [{username: req.body.name}, {nif: req.body.nif}] };
		/*if(req.body.password != req.body.confirmpassword){
			res.status(400).json({success:false, message:'Passwords dont match'});
		}*/
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
					whereClause = {	[Op.and]: [{type: req.body.cc.type}, {number: req.body.cc.number}] };
					Card
						.findOne({
							where: whereClause
						})
						.then(card => {
							if (!card) {
								let id = uuidv4();
								let password = bcrypt.hashSync(req.body.password, 10);
								User
									.create({
										id: id,
										username: req.body.name,
										password: password,
										nif: req.body.nif,
										keyN: req.body.keyN,
										keyE: req.body.keyE
									})
									.then(user => {
										if(user) {
										    let cardId = uuidv4();
											Card
												.create({
													id: cardId,
													type: req.body.cc.type,
													number: req.body.cc.number,
													validity: req.body.cc.validity,
													userId: id
												})
												.then(card=>{
													if(card){
														res.status(200).json({success:true, message:'User and credit card created', id: id});
													}
												});
										}
									});
							} else {
								res.status(400).json({
									success: false,
									message: 'Card already exists'
								});
							}
						})
						.catch(err=>{
							console.log(err);
							res.status(500).json({success: false, message: 'Error occured: ' + err});
						});
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
	checkMessage(req,res, next){
		User
			.findOne({
				where: {
					id: req.body.message.userId
				}
			})
			.then(user => {
				if(user){
					var key = rsa();
					key.importKey({
						n: Buffer.from(user.keyN, 'hex'),
						e: parseInt(user.keyE, 16)
					},'components-public');
					if(key.verify(req.body.message, req.body.messageSigned, 'utf-8', 'base64')){
						console.log('entrei');
						req.decoded = req.body;
						next();
					}
					else {
						console.log('falhei');
						return res.status(400).json({
							success: false, message: 'Invalid Signature'
						});
					}
				} else {
					return res.status(400).json({
						success:false, message:'User doesn\'t exist'
					});
				}
			})
			.catch(err => {
				return res.status(500).json({success: false, message: 'Error occurred: ' + err});
			});
	}
};

