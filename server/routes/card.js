const express = require('express'),
	router = express.Router(),
	cardController = require('../controllers/card');


router.get('/',
	cardController.list
);

module.exports = router;
