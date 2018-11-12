const express = require('express'),
    router = express.Router(),
    orderController = require('../controllers/order');


router.get('/',
    orderController.list
);

module.exports = router;
