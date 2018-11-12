const express = require('express'),
    router = express.Router(),
    userController = require('../controllers/user');

router.get('/',
    userController.list
);

module.exports = router;
