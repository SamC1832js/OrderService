# Shopping Cart API Documentation

This project is a basic e-commerce backend application built using **Spring Boot**. It provides APIs for managing users, shopping carts, orders, and products. The project is currently deployed locally on Spring Boot with no security implemented yet. In the future, **JWT token-based authorization** can be added to secure the API requests.

## 🚀 **How to Run the Project**
1. Clone the repository.
2. Ensure you have **Java** and **Spring Boot** installed.
3. Run the application using:
   ```
    mvn spring-boot:run
   ```
4. The default server port is **8080**.

Once the application is running, the APIs will be accessible at:
```
http://localhost:8080
```

---

## 📋 **Available APIs**
The following endpoints are available for interacting with the backend services:

### 🧑‍💻 **User Management**
**Base URL:** `/api/users`

- **GET** `/` - Retrieve user information using an email query parameter.
  - Example: `/api/users?email=john.doe@example.com`
- **POST** `/` - Register a new user in the database. Automatically creates a shopping cart for the user.
- **DELETE** `/{id}` - Delete a user by their **userId**.

### 🛒 **Shopping Cart Management**
**Base URL:** `/api/shoppingcart`

- **GET** `/{userId}` - Retrieve the shopping cart associated with a specific user.
- **PUT** `/{userId}/{productId}` - Add a product to the user's shopping cart.
- **PUT** `/{userId}/{productId}/{quantity}` - Update the quantity of a specific product in the user's shopping cart.

### 📦 **Order Management**
**Base URL:** `/api/orders`

- **GET** `/{userId}` - Retrieve orders associated with a specific user.
- **POST** `/{userId}` - Create a new order for the user based on the items in their shopping cart.
  - **Note:** Once an order is created, it cannot be modified or deleted by the user.

### 🛍️ **Product Management**
**Base URL:** `/api/products`

- **GET** `/` - Retrieve a list of all available products.
- **POST** `/` - Add a new product to the database.
- **DELETE** `/{id}` - Delete a product by its **productId**.

---

## 📌 **Future Enhancements**
- **JWT Token-Based Authorization**: Implement token-based security for API endpoints to ensure secure user authentication.
- **Order History**: Allow users to view their past orders in more detail.
- **Product Search and Filtering**: Add more advanced product search and filtering capabilities.

## 🛠 **Technologies Used**
- **Java**
- **Spring Boot**
- **PostgreSQL** (or any relational database)

## ⚠️ **Known Limitations**
- No security implemented yet.
- Basic error handling.

---

Thank you for visiting the Shopping Cart API project! Feel free to contribute or suggest enhancements. Happy coding! 😊

