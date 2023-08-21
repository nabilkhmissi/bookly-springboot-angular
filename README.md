# Book Store Application

Welcome to the Book Store Application! This project is a simple example of a book store web application developed using Spring Boot for the backend and Angular for the frontend. The application allows users to purchase books, add them to their carts, and explore a wide range of books.

## Live : https://boostorapp19.netlify.app
## For test : 
- email : user@mail.com
- password : user

## Features

- User Registration and Authentication using Spring Security and JWT.
- Book Catalog: Browse and search for a variety of books.
- Shopping Cart: Add books to your shopping cart for purchase.
- User Dashboard: Track your orders and cart contents.
- Reactive Programming: Utilizes Angular's RxJS for handling asynchronous operations.

## Technologies Used

### Backend

- Spring Boot: A Java framework for building production-grade applications.
- Spring Security: Provides authentication and authorization functionalities.
- JWT (JSON Web Tokens): Used for secure user authentication.
- MongoDB: A NoSQL database for storing book and user data.

### Frontend

- Angular: A popular frontend framework for building dynamic web applications.
- RxJS: A library for reactive programming to handle asynchronous operations.
- Bootstrap: A CSS framework for responsive and mobile-first designs.

## Setup Instructions

### Backend (Spring Boot)

1. Clone this repository to your local machine.
2. Make sure you have Java and Maven installed.
3. Configure MongoDB URI in `application.properties`.
4. Run the Spring Boot application using your preferred IDE or with `mvn spring-boot:run`.

### Frontend (Angular)

1. Navigate to the `frontend` directory.
2. Make sure you have Node.js and npm installed.
3. Install Angular CLI with `npm install -g @angular/cli`.
4. Run `npm install` to install the required dependencies.
5. Update the API base URL in `environment.ts`.
6. Run the Angular application with `ng serve`.

## Usage

- Visit `http://localhost:4200` in your browser to access the Angular frontend.
- Register or log in using your credentials.
- Explore the book catalog, add books to your cart, and proceed to checkout.
- View your user dashboard to see your order history and current cart contents.
