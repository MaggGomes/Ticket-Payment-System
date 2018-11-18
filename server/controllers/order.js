const
	Op = Sequelize.Op,
	request = require('request'),
	Voucher = require('../models/index').Voucher,
	User = require('../models/index').User,
	ProductOrder = require('../models/index').ProductOrder,
	OrderTransaction = require('../models/index').OrderTransaction,
	TicketTransaction = require('../models/index').TicketTransaction;

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
	},
	buy(req,res){
		User
			.findOne({
				where : {
					id: req.body.message.userId
				}
			})
			.then(user=>{
				if(!user){
					return res.status(400).json({success:false, message:'User id doesn\'t exist'});
				}
				if(req.body.message.vouchers.length > 2)
					return res.status(400).json({success:false, message:'Invalid number of vouchers'});

				var voucherIds = [];
				for(let i = 0; i< req.body.message.vouchers.length; i++){
					voucherIds.push(req.body.message.vouchers[i].id);
				}
				var count = 0;
				var checkProducts = req.body.message.products;
				var products = req.body.message.products;
				var voucher5 = false;
				for(let i = 0; i < req.body.message.vouchers.length; i++){
					if(req.body.message.vouchers[i].productId == 0 && voucher5 === false){
						voucher5 = true;
					}else if(req.body.message.vouchers[i].productId == 0 && voucher5 === true)
						return res.status(400).json({success:false, message:'More than one 5% voucher'});
					else {
						for(let j = 0; j < checkProducts.length; j++){
							if(checkProducts[j].id == voucherIds[i].productId && checkProducts[j].quantity > 0){
								count++;
								checkProducts[j].quantity = checkProducts[j].quantity - 1;
							}
						}
					}
				}
				if(count == req.body.message.vouchers.length){
					return res.status(400).json({success:false, message:'Invalid vouchers to the products received'});
				}
				Voucher
					.findAll({
						where: {
							userId: req.body.message.userId,
							id: {[Op.in] : voucherIds}
						}
					})
					.then(vouchers => {
						if (vouchers) {
							if(vouchers.length != req.body.message.vouchers.length)
								return res.status(400).json({success:false, message:'At least on voucher was invalid'});

							var spentBefore = 0;
							TicketTransaction
								.findAll({
									where : {
										userId: user.id
									}
								})
								.then(trans => {
									for(let i = 0; i < trans.length; i++){
										spentBefore += trans[i].totalPrice;
									}
									OrderTransaction
										.findAll({
											where: {
												userId : user.id
											},
											attributes: ['id']
										})
										.then(otrans=>{
											var ids = [];
											for(let i = 0; i < otrans.length; i++){
												ids.push(otrans[i].id);
											}
											ProductOrder
												.findAll({
													where: {
														orderId : {[Op.in] : ids}
													}
												})
												.then(orders=>{
													for(let i = 0; i < orders.length; i++){
														spentBefore += orders[i].totalPrice;
													}
													console.log('SPENT BEFORE: ' + spentBefore);
													var orderBulk = [];
													var freeProductIds = [];
													var totalPrice = 0;
													for(let i = 0; i < vouchers.length; i++) {
														for (let j = 0; j < products.length; j++) {
															if (vouchers[i].productId == products[j].id && products[j].quantity > 0 ) {
																freeProductIds.push(products[j].id);
																products[j].quantity = products[j].quantity - 1;
															}
														}
													}

													for(let i = 0; i < products.length; i++){
														totalPrice += products[i].price * products[i].quantity;
													}
													console.log(totalPrice);

													if(voucher5 == true)
														totalPrice = totalPrice * 0.95;

													OrderTransaction
														.create({
															userId: req.body.message.userId,
															date: Date.now(),
															totalPrice : totalPrice
														})
														.then(newOrder=> {
															for(let i = 0; i < freeProductIds.length; i++) {
																for (let j = 0; j < products.length; j++) {
																	if (products[j].id == freeProductIds[i]) {
																		products[j].quantity = products[j].quantity + 1;
																	}
																}
															}
															for(let i = 0; i < products.length; i++){
																orderBulk.push({
																	productId: products[i].id,
																	orderId: newOrder.id,
																	quantity: products[i].quantity,
																	totalPrice: products[i].quantity * products[i].price
																});
															}
															ProductOrder
																.bulkCreate(orderBulk)
																.then(()=>{
																	res.status(200).json({success:true, message:'order made', vouchers: vouchers, order: newOrder});
																})
																.catch(err => {
																	res.status(400).json({success: false, message: 'Error: ' + err});
																});
														})
														.catch(err => {
															res.status(400).json({success: false, message: 'Error: ' + err});
														});
												})
												.catch(err => {
													res.status(400).json({success: false, message: 'Error: ' + err});
												});
										})
										.catch(err => {
											res.status(400).json({success: false, message: 'Error: ' + err});
										});
								})
								.catch(err => {
									res.status(400).json({success: false, message: 'Error: ' + err});
								});
						} else {
							res.status(400).json({success: false, message: 'Show doesn\'t exist'});
						}
					})
					.catch(err => {
						console.log(err);
						res.status(500).json({success: false, message: 'Error occured: ' + err});
					});
			})
			.catch(err=>{
				console.log(err);
				res.status(500).json({success: false, message: 'Error occured: ' + err});
			});
	}
};