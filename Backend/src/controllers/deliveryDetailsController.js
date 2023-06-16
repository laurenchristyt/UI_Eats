const { pool } = require('../config/config');

const deliveryDetailsController = {
    addDetails : async (req, res) => {
        try {
            const {
                id,
                deliveryName,
                deliveryAddress,
                deliveryPhoneNumber,
                deliveryNote
            } = req.body;
            console.log(req.body);

            const addDeliveryDetails = `INSERT INTO DELIVERY (account_id, deliveryName, deliveryAddress, 
                deliveryPhoneNumber, deliveryNote) 
            VALUES ($1, $2, $3, $4, $5)
            `;
            const registerDeliveryValues = [
                id,
                deliveryName,
                deliveryAddress,
                deliveryPhoneNumber,
                deliveryNote,
                ]; const query = await pool.query(addDeliveryDetails, registerDeliveryValues);
                
                console.log(query);
                res.status(201).json({ message: 'Delivery Details registered successfully' });
                } catch (error) {
                console.error('Error', error);
                res.status(500).json({ message: 'Internal server error' });
        }
    }
}

module.exports = deliveryDetailsController;