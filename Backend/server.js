const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const app = express();
const port = 3000;
const { testDatabaseConnection } = require('./src/config/config');
const usersRoutes = require('./src/routes/userRoutes');
const deliveryRoutes = require('./src/routes/deliveryRoutes');
const topUpRoutes = require('./src/routes/topUpRoutes');


app.get('/', (req, res) => {
  res.send('Connection established');
});

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(cors());  

// Routes
app.use('/users', usersRoutes);
app.use('/deliveryDetails', deliveryRoutes);
app.use('/topUp', topUpRoutes);

testDatabaseConnection();

// Start the server
const PORT = process.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});