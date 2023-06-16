const { pool } = require('../config/config');

const burgerController = {
    addBurger : async (req, res) => {
        try{
            const {
                burger_type,
                burger_note
            } = req.body;
            

            const databaseBurger = `INSERT INTO BURGER (burger_type, burger_note)
            VALUES ($1, $2)`;

            const insertBurger = [
                burger_type,
                burger_note
            ];

            await pool.query(databaseBurger, insertBurger);

            res.status(200).json({ burger_type, burger_note});
        } catch (error) {
            console.error("Error creating customization", error);
            res.status(500).json({ message: 'Internal server error' });
        }
    }
};

module.exports = burgerController;