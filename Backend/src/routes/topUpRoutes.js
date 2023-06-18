const express = require('express');
const topUpController = require('../controllers/topUpController');

const router = express.Router();

// Define the route for retrieving pizza customization options
router.put('/:id/modify', topUpController.money);

module.exports = router;