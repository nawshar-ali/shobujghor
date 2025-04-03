# Shobujghor - Grocery Shopping Project

This repository contains the source code for a grocery shopping application built using Java and Spring Boot for the backend, and React for the frontend.

## Project Overview

This project implements a microservices architecture to provide a comprehensive grocery shopping experience. It includes features for user authentication, product management, cart operations, order processing, and more.

## Technologies Used

* **Backend:** Java, Spring Boot
* **Frontend:** React, Bootstrap
* **Authentication:** JWT
* **Communication:** REST APIs
* **Database:** (DynamoDB)
* **Cache:** Redis
* **Containerization:** Docker, Docker Compose

## Modules

The project is organized into 11 modules, each with specific functionalities:

1.  **authentication:**
    * Handles user signup, login, and email verification.
2.  **cart:**
    * Manages adding items to the cart, removing items from the cart, and checkout processes.
3.  **content-manager:**
    * Fetches and provides data for the application's home page.
4.  **gateway:**
    * Acts as an intermediary microservice, abstracting calls to other microservices. All client requests are routed through this gateway.
5.  **inventory:**
    * Manages item and category details and operations.
6.  **notification:**
    * Handles email notifications, such as verification emails.
7.  **order:**
    * Manages order confirmation and provides order details.
8.  **scripts:**
    * Contains scripts for project initialization, including table and queue creation, and dummy data insertion.
9.  **security:**
    * Handles JWT token creation and authentication configurations and utilities.
10. **utility:**
    * Contains utility classes and functions used throughout the project.
11. **web-app:**
    * The React-based frontend application for the grocery shopping project, utilizing basic Bootstrap for styling.

## Features

* User Authentication:
    * Login
    * Signup
    * Email verification
* Shopping Cart:
    * Add items to cart
    * Remove items from cart
    * Checkout
* Order Management:
    * View orders
* Product Catalog:
    * View Items
    * View Categories

## Getting Started

### Prerequisites

* Docker and Docker Compose
* JAVA 21
* Python 3.x (for `init.py`)
* Node.js and npm (Node Package Manager)

### Installation and Setup

1.  Clone the repository:

2.  Start the Docker Compose environment to set up the database and Redis:

    ```bash
    docker-compose up -d
    ```

3.  Run the `init.py` Python script to bootstrap the database with initial data:

    ```bash
    python scripts/init.py
    ```

4.  Navigate to each backend module and build the Spring Boot applications using Maven or Gradle.

5.  Navigate to the `web-app` directory and install the dependencies:

    ```bash
    cd web-app
    npm install
    ```

6.  Configure the application properties (database, Redis, etc.) in the Spring Boot modules as needed.

7.  Start the Spring Boot applications.

8.  Start the React frontend application:

    ```bash
    npm start
    ```
