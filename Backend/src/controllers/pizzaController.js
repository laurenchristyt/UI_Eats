
const { pool } = require('../config/config');

const pizzaController = {
    getCustomizationOptions: async (req, res) => {
      try {
        // Define the customization options for the pizza
        
        const {
          Topping
      } = req.body;
      console.log(req.body);

        const addPizza = ` INSERT INTO PIZZA (toppings) 
          VALUES ($1)
        `;
        const insertPizzaTopping = [
          Topping,
        ];

        await pool.query(addPizza, insertPizzaTopping);

        // Return the customization options as a response
        res.status(200).json({ Topping });
      } catch (error) {
        console.error('Error creating customization', error);
        res.status(500).json({ message: 'Internal server error' });
      }
    },
  };

  module.exports = pizzaController;