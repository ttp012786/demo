# Parcel Tracking System for Hotel Receptionists
--- This is a demo project, and the README.MD is generated with help from ChatGPT ---

## 📌 Overview
This project is a **reactive parcel tracking system** for hotel receptionists, designed to help manage parcels for guests. It ensures that receptionists can:
- **Track checked-in guests** to determine whether to accept parcels.
- **Manage parcel pickups** when guests check out.
- **Store and access data reactively** using **PostgreSQL with R2DBC**.
- **Expose RESTful APIs** documented with **Swagger/OpenAPI**.
- **Ensure high code quality** with **Testcontainers for integration testing**.

## ⚙️ Architectural Design
The system follows a **reactive programming model** using **Spring Boot WebFlux** and **R2DBC** for non-blocking database interactions. It is structured using **Domain-Driven Design (DDD)** principles to maintain modularity and scalability.

### **🔹 Technology Stack**
- **Java 11**
- **Spring Boot (WebFlux, R2DBC)**
- **PostgreSQL (Reactive R2DBC Driver)**
- **MapStruct (DTO ↔ Entity mapping)**
- **SpringDoc OpenAPI (API Documentation)**
- **Testcontainers (Integration Testing with PostgreSQL)**

## 🏗️ Design Approach
### **1️⃣ API-First Approach**
- The API contract is defined using **OpenAPI Specification (Swagger)**.
- Controllers, models, and services are implemented based on the API definition.
- API documentation is automatically generated and accessible via **Swagger UI**.

### **2️⃣ Domain-Driven Design (DDD)**
- The project is structured around **business domains**, ensuring clear separation of concerns.
- Core components include:
    - **Domain Layer** → Business logic and entities.
    - **Application Layer** → Services, DTOs, and use cases.
    - **Infrastructure Layer** → Repositories, R2DBC integration.
    - **Presentation Layer** → REST controllers, API endpoints.

## 🚀 Development Principles
### **✅ Production-Ready Code**
- Uses best practices for **naming conventions, coding style, and clean architecture**.
- Includes **meaningful comments and documentation** to enhance maintainability.
- Adopts **non-blocking, event-driven architecture** for scalability and performance.

### **✅ Test-Driven Development (TDD)**
- Unit tests ensure business logic correctness.
- Integration tests use **Testcontainers with PostgreSQL**.
- API tests validate request/response contracts.
- Uses **Reactor Test** for testing reactive components.

## 📌 Development Process
1. **Define API contract** → Design OpenAPI specification.
2. **Generate API stubs** → Use OpenAPI generator for controllers & models.
3. **Implement core business logic** → Develop domain and service layers.
4. **Develop API endpoints** → Implement REST controllers using WebFlux.
5. **Write unit & integration tests** → Follow TDD with JUnit, Testcontainers.
6. **Run & validate the system** → Deploy and test the production-ready system.

---

### 📖 API Documentation
Once the application is running, API documentation can be accessed at:
👉 `http://localhost:8080/swagger-ui.html`

### 🔧 Running Tests
```sh
mvn test
```

### 🚀 Running the Application
```sh
mvn spring-boot:run
```

---
### 📢 Contributing
Feel free to contribute by submitting issues or pull requests!

---
**© 2025 Parcel Tracking System. All rights reserved.**

