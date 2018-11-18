const express = require('express'),
	router = express.Router(),
	transactionController = require('../controllers/transaction');


router.get('/:id',
	transactionController.list
);

module.exports = router;
