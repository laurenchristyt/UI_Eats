const express = require('express');
const deliveryDetailsController = require('../controllers/deliveryDetailsController');

const router = express.Router();

// Define the route for retrieving pizza customization options
router.post('/:id/add', deliveryDetailsController.addDetails);

module.exports = router;