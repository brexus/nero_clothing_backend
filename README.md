# 🛒 NERO CLOTHING - E-Commerce Store
A fullstack e-commerce web application focused exclusively on clothing products. Users can browse products, select sizes, manage their cart, create an account and place orders.

#### FRONTEND - https://github.com/brexus/nero_clothing_frontend

### Main Features
- 👕 Product catalog with multiple available sizes per item
- 🏷️ Category browsing + pagination for product listing
- 📦 Real-time stock availability per product variant
- 🛒 Shopping cart management (add, remove, change quantity)
- 🔐 User authentication & registration
- 🧾 Order placement (guest checkout & authenticated users)
- 🖼️ Product images


## ⚙️ Tech Stack
### Frontend:
<a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/javascript/javascript-original.svg" alt="javascript" width="40" height="40"/> </a>
<a href="https://react.dev/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original.svg" alt="react" width="40" height="40"/> </a>

### Backend:
<a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a>
<a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a>

### Database:
<a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a>

## 🗃️ Database ERD Diagram
<img width="800" alt="ERD Diagram" src="https://github.com/user-attachments/assets/07c9b431-49af-4ee0-adad-af5b67f9b778" />

## 🖼️ Application Screenshots
#### Home page:
<img width="700" alt="home" src="https://github.com/user-attachments/assets/0661a83e-e47c-4030-9183-5c37743beecd" />

#### Cart page:
<img width="800" alt="cart" src="https://github.com/user-attachments/assets/635594bc-668e-407d-b3d0-da2ea77aab82" />

#### Checkout page (guest user):
<img width="800" alt="checkout-guest" src="https://github.com/user-attachments/assets/0796e576-39bd-49c9-b775-6b3b922efded" />

#### Checkout page (logged-in user):
<img width="800"  alt="cart order" src="https://github.com/user-attachments/assets/d7e8ab77-1d2b-4f59-8b80-6cd9ac64400f" />

#### Collection page:
<img width="900" alt="collections-all" src="https://github.com/user-attachments/assets/ca987e30-764a-41ae-8009-b9fd70ebd7d2" />

##### Pagination element:
<img width="250"  alt="pagination" src="https://github.com/user-attachments/assets/1ba867ba-3faf-471e-a9a0-6e92a8784078" />

#### Product page:
<img width="800" alt="product-1" src="https://github.com/user-attachments/assets/a12f5095-458f-4d6f-90eb-789f5d8abdea" />

#### Register component:
<img width="350" alt="register" src="https://github.com/user-attachments/assets/f05f2439-4f57-4eb7-a137-fec583c7b86a" />


#### Login component:
<img width="350" alt="login" src="https://github.com/user-attachments/assets/0b684bcf-96a3-443c-9c49-62784d9a2773" />


## 🚀 Getting Started
### 1️⃣ Clone the Repository
Backend (separate repository):

```bash
git clone https://github.com/SMS-PL/nero_clothing_backend
```
Frontend (separate repository):
```bash
git clone https://github.com/SMS-PL/nero_clothing_frontend
```

### 2️⃣ Database Setup (MySQL)
Before starting the backend, create an empty database for the application.

```sql
CREATE DATABASE nero_db;
```

### 3️⃣ Backend Setup (Spring Boot + Maven)
Navigate to the backend directory:

```bash
cd nero_clothing_backend
```

#### Install Dependencies:
```bash
mvn clean install
```

#### Configuration File:
Create a file named `application.properties` inside `src/main/resources/` with the following content:

```properties
spring.application.name=nero_clothing_backend
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/nero_db
spring.datasource.username=${your_mysql_username}
spring.datasource.password=${your_mysql_password}
spring.jpa.hibernate.ddl-auto=create
server.port=5000
server.servlet.context-path=/api/v1
spring.web.locale=en_US
jwt.secret=${your_jwt_secret_key}
jwt.expiration-ms=86400000
```

#### Run the Backend Server (to generate tables with Hibernate):
```bash
mvn spring-boot:run
```

### 4️⃣ Frontend Setup (React + Vite)

#### Navigate to frontend:
```bash
cd ../nero_clothing_frontend
```

#### Install Dependencies:
```bash
npm install
```

#### Run the Frontend Application:
```bash
npm run dev
```

### 5️⃣ Access the Application
- Backend API: http://localhost:8080
- Frontend app: http://localhost:5173

