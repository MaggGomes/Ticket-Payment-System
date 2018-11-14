const express = require('express'),
	router = express.Router(),
	ticketController = require('../controllers/ticket');

router.get('/',
	ticketController.list
);

module.exports = router;
