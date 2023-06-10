const express = require('express');
const pizzaController = require('../controllers/pizzaController');

const router = express.Router();

// Define the route for retrieving pizza customization options
router.get('/customization', pizzaController.getCustomizationOptions);


module.exports = router;