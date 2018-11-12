const express = require('express'),
      router = express.Router();

router.get('/', function(req, res, next) {
    res.status(200).json('CMOV');
});

module.exports = router;
