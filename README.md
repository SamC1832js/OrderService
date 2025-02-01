# API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication
The API uses JWT (JSON Web Token) for authentication. Most endpoints require a valid JWT token in the Authorization header.

Token expiration: 2 hours (7200000 milliseconds)

Format: `Authorization: Bearer <token>`

## Endpoints

### User API
Base path: `/api/users`

#### Register User
- **Method:** POST
- **Path:** `/api/users/register`
- **Description:** Registers a new user and creates an associated shopping cart
- **Authentication:** Not required
- **Request Body:**
```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string"
}
```
- **Response:** 201 CREATED
  - Message: "User registered successfully."

#### Login User
- **Method:** POST
- **Path:** `/api/users/login`
- **Description:** Authenticates user and returns JWT token
- **Authentication:** Not required
- **Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```
- **Response:** 200 OK
```json
{
  "token": "string"
}
```

#### Validate Token
- **Method:** GET
- **Path:** `/api/users/validateToken`
- **Description:** Validates if the current JWT token is valid
- **Authentication:** Required
- **Response:** 200 OK if valid, 401 UNAUTHORIZED if invalid

### Product API
Base path: `/api/products`

#### Get All Products
- **Method:** GET
- **Path:** `/api/products`
- **Description:** Retrieves all available products
- **Authentication:** Not required
- **Response:** 200 OK
  - Returns array of products

#### Get Product by ID
- **Method:** GET
- **Path:** `/api/products/{id}`
- **Description:** Retrieves a specific product by ID
- **Authentication:** Not required
- **Response:** 200 OK
  - Returns product details

#### Add Product
- **Method:** POST
- **Path:** `/api/products`
- **Description:** Creates a new product
- **Authentication:** Required
- **Request Body:**
```json
{
  "name": "string",
  "description": "string",
  "price": number,
  "category": "string",
  "brand": "string",
  "imgUrl": "string"
}
```
- **Response:** 201 CREATED
  - Returns created product

#### Update Product
- **Method:** PUT
- **Path:** `/api/products`
- **Query Parameters:** 
  - `productName` (required): Name of product to update
- **Description:** Updates all fields of an existing product
- **Authentication:** Required
- **Request Body:** Same as Add Product
- **Response:** 200 OK
  - Returns updated product

#### Partial Update Product
- **Method:** PATCH
- **Path:** `/api/products`
- **Query Parameters:**
  - `productName` (required): Name of product to update
- **Description:** Updates specific fields of a product
- **Authentication:** Required
- **Request Body:** Only include fields to update
```json
{
  "description": "string",
  "price": number
}
```
- **Response:** 200 OK
  - Returns updated product

#### Delete Product
- **Method:** DELETE
- **Path:** `/api/products`
- **Query Parameters:**
  - `productName` (required): Name of product to delete
- **Description:** Removes a product from the system
- **Authentication:** Required
- **Response:** 204 NO CONTENT

### Shopping Cart API
Base path: `/api/shoppingcart`

#### Get Shopping Cart
- **Method:** GET
- **Path:** `/api/shoppingcart`
- **Description:** Retrieves current user's shopping cart
- **Authentication:** Required
- **Response:** 200 OK
  - Returns shopping cart details

#### Add Product to Cart
- **Method:** POST
- **Path:** `/api/shoppingcart`
- **Description:** Adds a product to user's cart
- **Authentication:** Required
- **Request Body:**
```json
{
  "productName": "string",
  "quantity": number
}
```
- **Response:** 200 OK
  - Returns updated cart

#### Update Product Quantity
- **Method:** PUT
- **Path:** `/api/shoppingcart`
- **Description:** Updates product quantity in cart
- **Authentication:** Required
- **Request Body:** Same as Add Product to Cart
- **Response:** 200 OK
  - Returns updated cart

#### Remove Product from Cart
- **Method:** DELETE
- **Path:** `/api/shoppingcart`
- **Query Parameters:**
  - `productName` (required): Product to remove
- **Description:** Removes a product from cart
- **Authentication:** Required
- **Response:** 200 OK

#### Clear Cart
- **Method:** DELETE
- **Path:** `/api/shoppingcart/clear`
- **Description:** Removes all items from cart
- **Authentication:** Required
- **Response:** 200 OK

### Order API
Base path: `/api/orders`

#### Get All Orders
- **Method:** GET
- **Path:** `/api/orders`
- **Description:** Retrieves all orders for current user
- **Authentication:** Required
- **Response:** 200 OK
  - Returns array of orders

#### Get Order by ID
- **Method:** GET
- **Path:** `/api/orders/{id}`
- **Description:** Retrieves specific order details
- **Authentication:** Required
- **Response:** 200 OK
  - Returns order details

#### Create Order
- **Method:** POST
- **Path:** `/api/orders`
- **Description:** Creates a new order from current shopping cart contents
- **Authentication:** Required
- **Response:** 200 OK
  - Returns created order details

## Error Responses
- 400 Bad Request - Invalid input
- 401 Unauthorized - Missing or invalid authentication
- 403 Forbidden - Insufficient permissions
- 404 Not Found - Resource not found
- 500 Internal Server Error - Server error

## CORS Configuration
The API allows requests from:
- Origin: `http://localhost:4200`
- Methods: GET, POST, PUT, DELETE, OPTIONS
- Headers: All allowed
- Credentials: Allowed
