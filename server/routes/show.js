const express = require('express'),
	router = express.Router(),
	showController = require('../controllers/show');


router.get('/',
	showController.list
);

router.get('/:id',
	showController.retrieve
);

module.exports = router;
