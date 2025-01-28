# REST API Documentation

This document outlines the available API endpoints for the project. The application can be deployed using Spring Boot and currently does not have security measures implemented. Future enhancements will include JWT token-based authorization.

---

## **Base URL**
```
http://localhost:8080
```
The default server port is `8080`.

---

## **User API**
### **Endpoint:** `/api/users`

#### **POST** - Register a New User
- **Description:** Registers a new user and creates a shopping cart for the user.
- **URL:** `/api/users`
- **Body:**
```json
{
  "firstName": "first",
  "lastName": "last",
  "email": "example@example.com",
  "password": "password123"
}
```
- **Response:** `201 CREATED`
  - Returns the newly created user ID.

#### **GET** - Get User Information
- **Description:** Retrieves user information using email and password.
- **URL:** `/api/users`
- **Query Parameters:**
  - `email` (required)
  - `password` (required)
- **Response:** `200 OK`
  - Returns the user details.

#### **DELETE** - Delete a User
- **Description:** Deletes a user from the database.
- **URL:** `/api/users/{id}`
- **Query Parameters:**
  - `email` (required)
  - `password` (required)
- **Response:** `204 NO CONTENT`
  -   User has been deleted.
---

## **Product API**
### **Endpoint:** `/api/products`

#### **GET** - Get All Products
- **Description:** Retrieves a list of all available products.
- **URL:** `/api/products`
- **Response:** `200 OK`
  - Returns a list of products.

#### **POST** - Add a New Product
- **Description:** Adds a new product to the database.
- **URL:** `/api/products`
- **Body:**
```json
{
  "name": "Product Name",
  "description": "Product Description",
  "price": 19.99,
  "category": "CPU",
  "brand" : "intel",
  "imgurl" : "url"
}
```
- **Response:** `201 CREATED`

#### **PUT** - Update an Existing Product
- **Description:** Updates the details of an existing product.
- **URL:** `/api/products`
- **Query Parameter:**
  - `productName` (required)
- **Body:**
```json
{
  "name": "Product Name",
  "description": "Product Description",
  "price": 19.99,
  "category": "CPU",
  "brand" : "brand",
  "imgurl" : "url"
}
```
- **Response:** `200 OK`

#### **PATCH** - Partially Update a Product
- **Description:** Partially updates specific fields of a product.
- **URL:** `/api/products`
- **Query Parameter:**
  - `productName` (required)
- **Body:**
```json
{
  "description": "New Description",
  "price": 29.99
}
```
- **Response:** `200 OK`

#### **DELETE** - Delete a Product
- **Description:** Deletes a product by its name.
- **URL:** `/api/products`
- **Query Parameter:**
  - `productName` (required)
- **Response:** `204 NO CONTENT`
-   Product deleted successfully.
---

## **Shopping Cart API**
### **Endpoint:** `/api/shoppingcart`

#### **GET** - Get Shopping Cart by User ID
- **Description:** Retrieves the shopping cart of a specific user.
- **URL:** `/api/shoppingcart/{userId}`
- **Response:** `200 OK`

#### **POST** - Add Product to Shopping Cart
- **Description:** Adds a product to a user's shopping cart.
- **URL:** `/api/shoppingcart/{userId}`
- **Query Parameters:**
  - `productname` (required)
  - `quantity` (optional, default is 1)
- **Response:** `200 OK`

#### **PUT** - Update Product Quantity in Shopping Cart
- **Description:** Updates the quantity of a specific product in the user's shopping cart.
- **URL:** `/api/shoppingcart/{userId}`
- **Query Parameters:**
  - `productname` (required)
  - `quantity` (required)
- **Response:** `200 OK`
  - If quantity is `0`, the product will be removed from the cart.

#### **DELETE** - Remove Product from Shopping Cart
- **Description:** Remove a specific product in the user's shopping cart.
- **URL:** `/api/shoppingcart/{userId}`
- **Query Parameters:**
  - `productname` (required)
- **Response:** `200 OK`
  -  Product removed from your shopping cart.

#### **DELETE** - Clear Shopping Cart
- **Description:** Clears all items from the user's shopping cart.
- **URL:** `/api/shoppingcart/{userId}/clear`
- **Response:** `200 OK`
  -   All products removed from your shopping cart.

---

## **Order API**
### **Endpoint:** `/api/orders`

#### **GET** - Get Orders by User ID
- **Description:** Retrieves all orders associated with a specific user.
- **URL:** `/api/orders/{userId}`
- **Response:** `200 OK`
  - Returns a list of orders.

#### **POST** - Create an Order
- **Description:** Processes a new order using the products in the user's shopping cart, and remove all products from the shopping cart.
- Shopping cart can not be empty, it must contain some product to create an order.
- **URL:** `/api/orders/{userId}`
- **Response:** `200 OK`
  - Returns the created order.

---

## **Error Handling**
The API returns standard HTTP status codes to indicate the success or failure of requests:
- `200 OK` - Request was successful.
- `201 CREATED` - Resource was created successfully.
- `204 NO CONTENT` - Request was successful, but there is no content to return.
- `404 NOT FOUND` - The requested resource was not found.
- `400 BAD REQUEST` - The request was invalid or missing parameters.

---

## **Future Enhancements**
- Implement JWT token-based authorization for secure access to the APIs.
- Add validation and error messages for better user feedback.

---

## **Notes**
- The application does not have any security measures implemented yet. Use caution when deploying to a public environment.
- Ensure that the database is properly set up before making API calls.

