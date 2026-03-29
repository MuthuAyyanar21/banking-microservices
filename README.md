# 🏦 Banking Microservices System

A production-ready banking system built with Java Spring Boot Microservices architecture.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-green)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)

---

## 🏗️ Architecture
```
Client Request
      ↓
API Gateway (Port 8080)
      ↓
┌─────────────────────────────────┐
│         Eureka Server           │
│      Service Discovery          │
└─────────────────────────────────┘
      ↓              ↓           ↓
User Service   Account Service   Transaction Service
  (8081)          (8082)             (8083)
      ↓                                ↓
MySQL DB                          Kafka Events
                                       ↓
                              Notification Service
                                    (8084)
```

---

## 🚀 Microservices

| Service | Port | Description |
|---|---|---|
| Eureka Server | 8761 | Service Discovery |
| API Gateway | 8080 | Single Entry Point |
| User Service | 8081 | Authentication + JWT |
| Account Service | 8082 | Bank Account Management |
| Transaction Service | 8083 | Deposit, Withdraw, Transfer |
| Notification Service | 8084 | Real-time Alerts via Kafka |

---

## ⚙️ Tech Stack

| Category | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.2.3 |
| Service Discovery | Netflix Eureka |
| API Gateway | Spring Cloud Gateway |
| Inter-service calls | OpenFeign |
| Authentication | Spring Security + JWT |
| Messaging | Apache Kafka |
| Database | MySQL 8.0 |
| Documentation | Swagger UI (SpringDoc OpenAPI) |
| Containerization | Docker + Docker Compose |
| Build Tool | Maven |
| Version Control | Git + GitHub |

---

## 🐳 Run with Docker (Recommended)

### Prerequisites
- Docker Desktop installed and running
- Git installed

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/MuthuAyyanar21/banking-microservices.git
cd banking-microservices
```

**2. Start all services with ONE command**
```bash
docker-compose up --build
```

**3. Wait 2-3 minutes for all services to start**

**4. Access the services**
- Eureka Dashboard: http://localhost:8761
- API Gateway: http://localhost:8080
- User Service Swagger: http://localhost:8081/swagger-ui/index.html
- Account Service Swagger: http://localhost:8082/swagger-ui/index.html
- Transaction Service Swagger: http://localhost:8083/swagger-ui/index.html

**5. Stop all services**
```bash
docker-compose down
```

---

## 📡 API Endpoints

### Authentication (User Service)
```
POST /api/auth/register   → Register new user
POST /api/auth/login      → Login and get JWT token
GET  /api/auth/health     → Health check
```

### Account Management (Account Service)
```
POST /api/accounts/create           → Create bank account
GET  /api/accounts/user/{userId}    → Get accounts by user
GET  /api/accounts/{accountNumber}  → Get account details
GET  /api/accounts/all              → Get all accounts
```

### Transactions (Transaction Service)
```
POST /api/transactions/deposit              → Deposit money
POST /api/transactions/withdraw             → Withdraw money
POST /api/transactions/transfer             → Transfer between accounts
GET  /api/transactions/history/{accountNo} → Transaction history
```

---

## 🧪 API Testing

### 1. Register a user
```bash
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "email": "muthu@gmail.com",
  "password": "password123",
  "fullName": "Muthu Ayyanar",
  "phone": "9025529291"
}
```

### 2. Login and get JWT token
```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "muthu@gmail.com",
  "password": "password123"
}
```

### 3. Create a bank account
```bash
POST http://localhost:8080/api/accounts/create
Content-Type: application/json

{
  "userId": 1,
  "accountType": "SAVINGS"
}
```

### 4. Deposit money
```bash
POST http://localhost:8080/api/transactions/deposit
Content-Type: application/json

{
  "accountNumber": "YOUR_ACCOUNT_NUMBER",
  "amount": 5000,
  "description": "Initial deposit"
}
```

### 5. Check transaction history
```bash
GET http://localhost:8080/api/transactions/history/{accountNumber}
```

---

## 🔐 Security

- JWT tokens for authentication
- BCrypt password encryption
- Spring Security configuration
- Stateless session management

---

## 📁 Project Structure
```
banking-microservices/
├── eureka-server/          → Service Discovery
├── api-gateway/            → API Gateway
├── user-service/           → Auth + JWT
├── account-service/        → Bank Accounts
├── transaction-service/    → Transactions
├── notification-service/   → Kafka Notifications
├── docker-compose.yml      → Docker Orchestration
├── init.sql                → Database initialization
└── README.md
```

---

## 👨‍💻 Developer

**Muthu Ayyanar S**
- B.Tech Computer Science and Business Systems
- PSNA College of Engineering and Technology
- GitHub: [@MuthuAyyanar21](https://github.com/MuthuAyyanar21)
- Email: muthuayyanarselvaraj@gmail.com
- LinkedIn: [muthuayyanars21](https://linkedin.com/in/muthuayyanars21)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).