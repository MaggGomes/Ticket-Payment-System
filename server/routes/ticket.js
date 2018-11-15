const express = require('express'),
	router = express.Router(),
	ticketController = require('../controllers/ticket'),
	userController = require('../controllers/user');

router.get('/',
	ticketController.list
);

router.post('/buy',
	userController.retrieveProtectedInfo,
	ticketController.create
);
module.exports = router;
