# Shopping Cart API Documentation

This is a simple e-commerce backend application built with **Spring Boot**. It offers APIs for managing users, shopping carts, orders, and products. Currently, it runs locally with no security features. Future updates may include **JWT token-based authorization**.

## 🚀 **Running the Project**
1. Clone the repository.
2. Ensure **Java** and **Spring Boot** are installed.
3. Run the app with:
   ```
    mvn spring-boot:run
   ```
4. Access the APIs at:
```
http://localhost:8080
```

---

## 📋 **Available APIs**
### 🧑‍💻 **User Management**
**Base URL:** `/api/users`
- **GET** `/` - Retrieve user info using an email query.
- **POST** `/` - Register a new user and create a shopping cart.
- **DELETE** `/{id}` - Delete user by **userId**.

### 🛒 **Shopping Cart Management**
**Base URL:** `/api/shoppingcart`
- **GET** `/{userId}` - Retrieve user's shopping cart.
- **PUT** `/{userId}/{productId}` - Add a product to the cart.
- **PUT** `/{userId}/{productId}/{quantity}` - Update product quantity in the cart.

### 📦 **Order Management**
**Base URL:** `/api/orders`
- **GET** `/{userId}` - Retrieve user orders.
- **POST** `/{userId}` - Create an order from the shopping cart.

### 🛍️ **Product Management**
**Base URL:** `/api/products`
- **GET** `/` - List all products.
- **POST** `/` - Add a new product.
- **DELETE** `/{id}` - Delete a product by **productId**.

---

## 📌 **Future Enhancements**
- **JWT Token-Based Authorization** for secure API access.
- **Product Search and Filtering** 

## 🛠 **Technologies Used**
- **Java**
- **Spring Boot**
- **PostgreSQL**

## ⚠️ **Known Limitations**
- No security features implemented.
- No error handling.

---

Thank you for checking out the Shopping Cart API project! Contributions and suggestions are welcome. Happy coding! 😊
