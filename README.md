# Orders API Endpoint
## Description
This project is a component of a larger software system, specifically focusing on the "Orders and Notifications Management" module. The purpose of this module is to handle online purchase orders and manage message notifications based on various actions taken during the order process.
## Note
You can try the API using **Postman** by using the `postman_collection.json` file in the root directory.
## Module Features
1. **Product Listing**:
    - Display all products available for purchase, including their details (serial number, name, vendor, category, price) and remaining count per category.
2. **Account Management**:
    - Customers can create accounts and add a balance for future purchases.
3. **Order Placement**:
    - Simple Orders: Customers can place orders for single or multiple products.
    - Compound Orders: Customers can include orders for themselves and nearby friends to reduce shipping fees.
4. **Order Details**:
    - List all details of both simple and compound orders.
5. **Shipping Orders**:
    - Deduct shipping fees from the customer’s account for simple orders or from all participants for compound orders.
6. **Notification Management**:
    - Manage notification templates, languages, and placeholders.
    - Notification queue to manage notifications to be sent.

## API Endpoints
| Method | Endpoint | Description |
| --- | --- | --- |
| **`POST`** | `/customers/login` | Logins a customer |
| **`POST`** | `/customers/register` | Registers a new customer |
| **`GET`** | `/products` | Lists all products |
| **`GET`** | `/orders` | Lists all orders |
| **`GET`** | `/orders/simple` | Lists all simple orders |
| **`POST`** | `/orders/simple` | Adds a new simple order |
| **`GET`** | `/orders/compound` | Lists all compound orders |
| **`POST`** | `/orders/compound` | Adds a new compound order |
| **`POST`** | `/shipping/simple` | Ships a simple order |
| **`POST`** | `/shipping/compound` | Ships a compound order |
| **`GET`** | `/notifications` | Lists all notifications in the notifications queue |
