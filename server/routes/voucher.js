const express = require('express'),
    router = express.Router(),
    voucherController = require('../controllers/voucher');


router.get('/',
    voucherController.list
);

module.exports = router;
