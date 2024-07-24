# Customer Management CRUD Application

## Overview

This project is a simple CRUD (Create, Read, Update, Delete) application for managing customer data. The backend is built using JSP and Servlets with a MySQL database, and the frontend is implemented with basic HTML forms and tables. The application includes JWT authentication for securing the endpoints. Additionally, there is a feature to sync customer data from a remote API.

## Features

- **User Authentication**: Users must log in to access the application. JWT is used for authentication.
- **Customer Management**:
  - Create a new customer
  - View a list of customers with pagination, sorting, and searching
  - View details of a single customer
  - Update an existing customer
  - Delete a customer
- **Data Sync**: Fetch and synchronize customer data from a remote API.

## Technologies Used

- **Backend**: JSP, Servlets
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Token)

## Project Structure



```plaintext
src/
├── main/
│ ├── java/
│ │ ├── com/
│ │ │ ├── example/
│ │ │ │ ├── controller/
│ │ │ │ │ ├── AuthServlet.java
│ │ │ │ │ ├── CustomerServlet.java
│ │ │ │ ├── model/
│ │ │ │ │ ├── Customer.java
│ │ │ │ ├── service/
│ │ │ │ │ ├── AuthService.java
│ │ │ │ ├── dao/
│ │ │ │ │ ├── CustomerDAO.java
│ │ │ │ ├── util/
│ │ │ │ │ ├── JwtUtil.java
│ │ │ │ ├── filter/
│ │ │ │ │ ├── JwtFilter.java
│ ├── resources/
│ │ ├── META-INF/
│ ├── webapp/
│ │ ├── WEB-INF/
│ │ │ ├── web.xml
│ │ │ ├── views/
│ │ │ │ ├── login.jsp
│ │ │ │ ├── customer_list.jsp
│ │ │ │ ├── customer.jsp
│ │ │ │ ├── error.jsp

```
## Setup and Running the Project

### Prerequisites

- Java Development Kit (JDK)
- Apache Tomcat (or any servlet container)
- MySQL Database

### Steps

1. **Clone the Repository**:
   ```sh
   git clone (https://github.com/datteshkumarpallapu/customer_crud_app.git)
   cd MajorProject


2. **Setup the Database**:

   - Create a MySQL database named `customerdb`.
   - Create the `customers` table.

  ```sh 
  CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    street VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255)
);
```
3. **Configure Database Connection**:

   -   Ensure your MySQL database username and password are correctly set in context.xml.
4. **Build and Deploy**:

   - Compile the project and deploy it on a servlet container like Apache Tomcat.
   - Access the application via the browser at http://localhost:8080/MajorProject.
5. **API Authentication**:

   - Use the provided authentication API to fetch the Bearer token.
   - Store this token in the browser's local storage and include it in subsequent API calls.

# Usage

1. **Login** :

   - Open the application and log in using the provided credentials.
   - Example credentials:

     -  Username: admin
     - Password: password




2. **Customer Management**:

   - **Create Customer**: Navigate to the add customer form, fill in the details, and submit.
   - **View Customers**: Access the customer list page to view all customers.
   - **Edit Customer**: Click on a customer to view details and edit them.
   - **Delete Customer**: Click on the delete button next to a customer to remove them.
3. **Data Sync**:

   - Click the "Sync" button on the customer list page to fetch and update customer data from the remote API.

![list](https://gist.github.com/user-attachments/assets/626010d6-fe41-4674-b295-b629b9b7646e)

# Code Explanation
## Authentication
- **AuthServlet**: Handles user login and token generation.
- **JwtUtil**: Utility class for generating and validating JWT tokens.
- **JwtFilter**: Servlet filter to check for valid JWT tokens in incoming requests.
## Customer Management
- **CustomerServlet**: Manages CRUD operations for customers.
- **CustomerDAO**: Contains business logic for customer operations.
- **Customer**: Represents the customer entity.
## JSP Pages
- **login.jsp**: Login form for user authentication.

![login][def]
- **customer_list.jsp**: Displays a list of customers.
s
![data](https://gist.github.com/user-attachments/assets/9fa1109c-2e38-493b-a09e-b499f20ba175)
- **customer-form.jsp**: Form for creating or editing a customer.


![form](https://gist.github.com/user-attachments/assets/1779c7aa-4535-4bde-adb3-91f9b0db0b1e)
- **error.jsp**: Display the exception.


![error](https://gist.github.com/user-attachments/assets/d5d4ab42-f6d1-4ce7-98fb-cb586a6abaf4)

## Conclusion

Thank you for checking out the Customer CRUD Application! This project aims to provide a robust solution for managing customer data with secure authentication and a user-friendly interface. Whether you're looking to integrate similar functionality into your own applications or contribute to the development of this project, we hope you find it valuable.

If you encounter any issues or have suggestions for improvement, please feel free to open an issue or submit a pull request. Your feedback and contributions are greatly appreciated.

For further details, documentation, or to get involved with the project, please refer to the sections above or contact the project maintainers.

Happy coding!

## Acknowledgments

- [Project Contributors](#contribution) - Thanks to everyone who has contributed to the project.
- [Libraries and Tools](#prerequisites) - Special thanks to the libraries and tools that made this project possible.



[def]: https://gist.github.com/user-attachments/assets/87572e31-3e6a-4398-ba07-9387a31890eb
