# Livit Spring Backend

REST API backend for the Livit platform built with Spring Boot 3 and Java 21.

## Tech Stack

- **Java 21** + **Spring Boot 3.4.5**
- **Spring Security** — stateless JWT authentication
- **Spring Data JPA** + **Hibernate** — ORM with MySQL
- **JJWT 0.12.6** — JWT token generation and validation
- **Lombok** — boilerplate reduction
- **BCrypt** — password hashing
- **Bean Validation** — request DTO validation

## Prerequisites

- Java 21+
- Maven 3.9+
- MySQL 8+

## Database Setup

Create a MySQL database and user:

```sql
CREATE DATABASE livit_db;
CREATE USER 'livit_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON livit_db.* TO 'livit_user'@'localhost';
FLUSH PRIVILEGES;
```

## Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/livit_db
spring.datasource.username=livit_user
spring.datasource.password=your_password

jwt.secret=your-secret-key-at-least-32-bytes-long
jwt.expiration-ms=2592000000
```

> The JWT secret must be at least 32 characters (256 bits) for HMAC-SHA256.

## Running

```bash
./mvnw spring-boot:run
```

The server starts on **port 3005**.

## Project Structure

```
src/main/java/com/codewithali/livitspringbackend/
├── LivitSpringBackendApplication.java
├── auth/
│   ├── AuthController.java          # POST /auth/signup, /auth/login
│   ├── JwtService.java              # Token generation, validation, parsing
│   ├── JwtAuthenticationFilter.java # Extracts JWT from requests
│   └── dto/
│       ├── LoginRequest.java
│       └── LoginResponse.java
├── member/
│   ├── Member.java                  # JPA entity
│   ├── MemberController.java        # GET /members/me
│   ├── MemberRepository.java
│   ├── MemberService.java           # Signup & login logic
│   ├── dto/
│   │   ├── SignupRequest.java
│   │   └── MemberResponse.java
│   └── enums/
│       ├── MemberType.java          # USER, ADMIN, AGENT
│       ├── MemberStatus.java        # ACTIVE, BLOCKED, DELETE
│       └── MemberAuthType.java      # EMAIL, PHONE, TELEGRAM
├── config/
│   └── SecurityConfig.java          # Security filter chain, BCrypt bean
└── exception/
    └── GlobalExceptionHandler.java
```

## API Endpoints

### Public (no auth required)

| Method | Endpoint        | Description         |
|--------|-----------------|---------------------|
| POST   | `/auth/signup`  | Register a member   |
| POST   | `/auth/login`   | Login, get JWT token|

### Protected (Bearer token required)

| Method | Endpoint       | Description              |
|--------|----------------|--------------------------|
| GET    | `/members/me`  | Get current member profile|

### Signup

```http
POST http://localhost:3005/auth/signup
Content-Type: application/json

{
  "memberNick": "Ali",
  "memberPhone": "01058649977",
  "memberPassword": "1111111"
}
```

**Validation rules:**
- `memberNick` — required, 3-50 chars, unique
- `memberPhone` — required, 5-50 chars, unique
- `memberPassword` — required, 6-100 chars

### Login

```http
POST http://localhost:3005/auth/login
Content-Type: application/json

{
  "memberNick": "Ali",
  "memberPassword": "1111111"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "member": {
    "id": 1,
    "memberNick": "Ali",
    "memberPhone": "01058649977",
    "memberType": "USER",
    "memberStatus": "ACTIVE",
    ...
  }
}
```

### Get Current Member

```http
GET http://localhost:3005/members/me
Authorization: Bearer <token>
```