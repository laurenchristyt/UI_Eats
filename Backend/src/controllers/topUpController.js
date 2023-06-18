const bcrypt = require('bcrypt');
const { pool } = require('../config/config');

const topUpController = {
    money: async (req, res) => {
        try {
          const { 
            id, 
            balance
          } = req.body;
          console.log(req.body);
    
            const topUpQuery =
            'UPDATE Account SET balance = $1 WHERE account_id = $2';
            const topUpValues = [balance, id];
            const topUpResult = await pool.query(
                topUpQuery,
                topUpValues
            );

            console.log(topUpResult);
            res.status(201).json({ message: 'Delivery Details registered successfully' });
        } catch (error) {
        console.error('Error', error);
        res.status(500).json({ message: 'Internal server error' });
        }
    }
}

module.exports = topUpController;