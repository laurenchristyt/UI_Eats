const express = require('express');
const burgerController = require('../controllers/burgerController');

const router = express.Router();

// Define the route for retrieving pizza customization options
router.post('/customization', burgerController.addBurger);

module.exports = router;