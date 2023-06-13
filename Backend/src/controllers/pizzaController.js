
const { pool } = require('../config/config');

const pizzaController = {
    getCustomizationOptions: async (req, res) => {
      try {
        // Define the customization options for the pizza
    
        const {
          account_id,
          Topping,
          Crust
      } = req.body;
      console.log(req.body);

        const addPizza = ` INSERT INTO PIZZA (account_id, toppings, crust) 
          VALUES ($1, $2, $3)
        `;
        const insertPizzaTopping = [
          account_id,
          Topping,
          Crust
        ];

        await pool.query(addPizza, insertPizzaTopping);

        // Return the customization options as a response
        res.status(200).json({ account_id, Topping, Crust });
      } catch (error) {
        console.error('Error creating customization', error);
        res.status(500).json({ message: 'Internal server error' });
      }
    },
  };

  module.exports = pizzaController;