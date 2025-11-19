# 🛒 E-Shop Backend

A modern, scalable, and production-ready e-commerce backend built with Spring Boot. This project features a robust clean architecture, featuring high-performance caching with Redis, secure JWT authentication, and flexible product management backed by MySQL.


## 🚀 Features

### 🧩 Core Functionality
* Product catalog
* Search by keyword
* Pagination & sorting (ASC/DESC)
* Filtering by brand & product type
* Product details
* Shopping basket (Redis-based)
* Order creation & listing
* User registration & login
* Secure endpoints protected with JWT

### 🛡 Security
* Spring Security
* JWT Authentication
* Role-based endpoint protection
* Password hashing (BCrypt)

### ⚙ Architecture / Engineering
* Spring Data JPA + Specifications
* MySQL for relational data
* Redis for caching & basket storage
* Log4j2 for structured logging
* Docker support
* GitHub branch workflow (branching & PRs)
* Request validation (jakarta.validation)
* Swagger OpenAPI documentation
* Clean DTO-based architecture

## 📁 Project Structure

```text
eshop-backend/
 ├── controller/
 ├── service/
 ├── entity/
 ├── repository/
 ├── dto/
 ├── security/
 ├── specification/
 ├── config/
 ├── exception/
 ├── mapper/
 ├── resources/
 │    └── application.yml
 ├── pom.xml
 └── Dockerfile
```
## 🧰 Tech Stack

| Layer | Technology |
| :--- | :--- |
| **Backend** | Spring Boot 3, Spring Web |
| **Database** | MySQL |
| **Cache** | Redis |
| **Security** | Spring Security, JWT |
| **ORM** | Hibernate + Spring Data JPA |
| **Build Tool** | Maven |
| **Documentation** | Swagger / OpenAPI |
| **Logging** | Log4j2 |
| **Testing** | Postman |
| **Containerization** | Docker |
| **Version Control** | Git + GitHub Workflow |

## ⚙️ Installation & Running the Backend

You can run the backend either locally or using Docker.

### 🎯 1. Run Locally

#### ✅ Prerequisites
Before running, install:

* Java 17+
* Maven 3+
* MySQL 8+
* Redis
* Git

### 🏗 Step 1: Clone the Repository

```bash
git clone [https://github.com/DimitrisDimas/eshop-backend.git](https://github.com/DimitrisDimas/eshop-backend.git)
cd eshop-backend
````
### 🗄 Step 2: Create MySQL Database

```sql
CREATE DATABASE eshop;
```

Update database credentials in `application.yml` if necessary:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/eshop
    username: root
    password: yourpassword
```

### 🔄 Step 3: Start Redis

```Local
  redis-server
```
```Docker
  docker run -p 6379:6379 redis
```

### 📦 Step 4: Build the Project
```
  mvn clean install
```

### ▶️ Step 5: Run the Application
```
  mvn spring-boot:run
```
Your backend will run on:
```
 http://localhost:8081
```

🐳 2. Run With Docker
Step 1: Build the Docker image
```
 docker build -t eshop-backend 
```
### Step 2: Run default containers

If you'd like, a `docker-compose.yml` can be generated on request.

Example:

```
version: '3'
services:
  mysql:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: eshop
    ports:
      - "3306:3306"
  redis:
    image: redis
    ports:
      - "6379:6379"
  backend:
    image: eshop-backend
    ports:
      - "8080:8080"
    depends_on:
      - mysql
      - redis
```

Run:

`docker compose up`

* * *

# 📘 API Documentation (Swagger)


Swagger UI is available at:

🔗 **http://localhost:8081/swagger-ui/index.html**

This includes:

-   Product API
    
-   Basket API
    
-   Order API
    
-   Auth API
    
-   Pagination
    
-   Sorting
    
-   Example requests
    

* * *

# 🔑 Authentication

### Register

`POST /api/auth/register`

### Login


`POST /api/auth/login`

Login response:

```
{
  "username": "demo",
  "token": "your.jwt.token" 
}
```

Use the token:

`Authorization: Bearer <token>`

* * *

# 🧺 Basket (Redis)


-   Stored in Redis for fast performance
    
-   Basket operations are O(1)
    
-   Ideal for scalable e-commerce systems
    

Example:

`GET /api/baskets/{basketId} POST /api/baskets DELETE /api/baskets/{basketId}`

* * *

# 📦 Products API


Supports:

-   Pagination
    
-   Sorting
    
-   Keyword search
    
-   Filter by brand
    
-   Filter by type
    

Example:

`GET /api/products?page=0&size=10&sort=name&order=asc&brandId=1&typeId=3&keyword=laptop`

* * *

# 🛒 Order API


`POST /api/orders GET /api/orders GET /api/orders/paged DELETE /api/orders/{id}`

* * *

# 📄 Logging


Log4j2 is used for:

-   Authentication logs
    
-   Service event logs
    
-   Request/response logs
    
-   Error handling logs
    

* * *

# 🔀 GitHub Workflow

-   Branch-per-feature strategy
    
-   Pull requests
    
-   Code review
    
-   Merging after approval
    

Following industry standards.

* * *

# 📌 NextSteps


-    Add online payments (Stripe / PayPal)
    
-    Add product inventory management
    
-    Admin dashboard endpoints
    
-    Full global exception handler
    
-    Enhanced integration testing
        

* * *

# 🤝 Contributing


1.  Fork the repository
    
2.  Create your feature branch
    
3.  Commit your changes
    
4.  Open a Pull Request
    
5.  Wait for review
    
6.  Merge after approval
