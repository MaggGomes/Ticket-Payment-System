const express = require('express'),
	router = express.Router(),
	userController = require('../controllers/user');

router.get('/',
	userController.list
);

router.post('/register',
	userController.create
);
module.exports = router;
