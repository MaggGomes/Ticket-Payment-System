const express = require('express'),
	router = express.Router(),
	showController = require('../controllers/show');


router.get('/',
	showController.list
);

module.exports = router;
