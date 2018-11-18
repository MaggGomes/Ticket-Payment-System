const express = require('express'),
	router = express.Router(),
	orderController = require('../controllers/order');


router.get('/',
	orderController.list
);

router.put('/buy',
	orderController.buy
);


module.exports = router;
