const express = require('express'),
	router = express.Router(),
	ticketController = require('../controllers/ticket'),
	userController = require('../controllers/user');

router.get('/',
	ticketController.list
);

router.post('/buy',
	userController.checkMessage,
	ticketController.create
);

router.put('/present',
	ticketController.present
);

module.exports = router;
