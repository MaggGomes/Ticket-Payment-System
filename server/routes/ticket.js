const express = require('express'),
	router = express.Router(),
	ticketController = require('../controllers/ticket');

router.get('/',
	ticketController.list
);

router.post('/buy',
	ticketController.create
);
module.exports = router;
