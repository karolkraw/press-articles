# Press Articles

## General Information
Simple CRUD application created for learning purposes

## Setup

### Requirements:
- Java Development Kit (JDK) version 8 or above installed on your machine
- MySQL version 5.7 or above installed on your machine

### To run the application:
- Open MySQL command-line client or any other GUI tool to create a database and execute the following command: <br>
``CREATE DATABASE press-articles``
- Just execute the following commands in the root directory: <br>
``https://github.com/karolkraw/press-articles.git`` <br>
``mvnw clean package`` <br>
``java -jar target/press-articles-1.0-SNAPSHOT.jar``


## Rest-API Endpoints





| Endpoint         | Method | Description                                     | Validation Rules     | Request                | Response |
|------------------|--------|-------------------------------------------------|----------------------|------------------------|----------|
| `api/v1/article`     | POST   | Adds a new article                              | `name`: length between 1 and 50 characters.<br>- `description`: Optional, length up to 255 characters.<br>- `price`: Required, a floating-point number greater than 0.                | JSON BODY              | JSON     |
| `api/v1/article/{id}`    | GET    | Gets an article by id                           | `id`: int greater than 0                                                                                                                                                                        | PATH VARIABLE (id)     | JSON     | 
| `api/v1/article/{id}` | PATCH  | Updates an existing article                     | `id`: int greater than 0                                                                                                                                                                        | PATH VARIABLE (id)     |     JSON     |
| `api/v1/article/{id}`       | DELETE | Deletes an article by id                        | `id`: int greater than 0                                                                                                                                                                        | PATH VARIABLE (id)     |  JSON        |
| `api/v1/matching-articles/{keyword}`     | GET    | Gets all articles containing keyword in content | - `keyword`: string between 1 and 50 characters                                                                                                                                                 | PATH VARIABLE (String) |     JSON     |
| `api/v1/articles` | GET    | Gets all atricles in descending order           | - N/A                                                                                                                                                                                           | N/A                    |      JSON    |


