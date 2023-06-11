const pizzaController = {
    getCustomizationOptions: async (req, res) => {
      try {
        // Define the customization options for the pizza
        const toppings = ['pepperoni', 'mushrooms', 'onions'];
        const crustTypes = ['thin crust', 'deep dish', 'stuffed crust'];
        const sauces = ['tomato', 'alfredo', 'bbq'];
        const sizes = ['small', 'medium', 'large'];

        // Return the customization options as a response
        res.status(200).json({ toppings, crustTypes, sauces, sizes });
      } catch (error) {
        console.error('Error retrieving customization options', error);
        res.status(500).json({ message: 'Internal server error' });
      }
    },
  };

  module.exports = pizzaController;