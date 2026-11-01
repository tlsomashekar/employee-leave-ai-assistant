# Spring AI Leave Assistant

A Spring Boot application that demonstrates how to use Spring AI with Google Gemini for conversational AI, tool calling, and retrieval-augmented generation (RAG) in a leave-management scenario.

The project combines:

- A simple AI chat endpoint
- Structured extraction of leave requests from natural language
- Tool-based HR assistance for leave balance, policy lookup, and working-day calculation
- Knowledge-base Q&A using a vector store
- OpenAPI documentation via Springdoc

## Features

### 1. General AI chat
Use the `/ai` endpoint to send a natural-language prompt to Gemini.

### 2. Leave request extraction
The `/leave` endpoint extracts a `LeaveRequest` payload from free-form employee messages.

### 3. HR tool calling
The application exposes tools for:

- `getLeaveBalance(employee)`
- `getLeavePolicy()`
- `countWorkingDays(startDate, endDate)`

These tools are used by the `/leave-balance` and `/leave-assessment` endpoints.

### 4. Leave assessment workflow
The `/leave-assessment` endpoint evaluates a leave request using the available tools and returns an AI-generated answer grounded in the retrieved policy and balance data.

### 5. Knowledge-base Q&A with vector search
The `/knowledge/ask` endpoint answers questions using a `SimpleVectorStore` preloaded with company documents about annual leave, carry-forward rules, and sick leave.

## Tech stack

- Java 21
- Spring Boot 4.1.1
- Spring AI 2.0.1
- Google GenAI integration via Spring AI
- Spring MVC
- Springdoc OpenAPI
- Simple in-memory vector store

## Project structure

```text
src/
├── main/
│   ├── java/com/java/example/springai/
│   │   ├── config/
│   │   │   ├── KnowledgeConfig.java
│   │   │   └── OpenApiConfig.java
│   │   ├── controller/
│   │   │   ├── ChatController.java
│   │   │   └── KnowledgeController.java
│   │   ├── model/
│   │   │   ├── LeaveAssessmentRequest.java
│   │   │   └── LeaveRequest.java
│   │   ├── service/
│   │   │   ├── DateCalculationService.java
│   │   │   ├── EmployeeService.java
│   │   │   └── LeavePolicyService.java
│   │   └── SpringaiApplication.java
│   └── resources/
│       └── application.yml
└── test/java/com/java/example/springai/SpringaiApplicationTests.java
```

## Prerequisites

Before running the app, make sure you have:

- Java 21+
- Maven or the included Maven wrapper (`mvnw`)
- A Google Gemini API key

## Configuration

Set the Gemini API key as an environment variable:

```bash
export GEMINI_API_KEY="your_api_key_here"
```

On Windows PowerShell:

```powershell
$env:GEMINI_API_KEY="your_api_key_here"
```

The application reads this key from `application.yml`:

```yaml
spring:
  ai:
    google:
      genai:
        api-key: ${GEMINI_API_KEY}
```

## Running the application

From the project root:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
mvnw.cmd spring-boot:run
```

The app starts on:

```text
http://localhost:8080
```

## API endpoints

### General chat

```http
GET /ai?message=What is the leave policy?
```

### Extract leave request

```http
GET /leave?message=John wants 3 days of annual leave from 2026-11-10 to 2026-11-12
```

### Leave balance query

```http
GET /leave-balance?message=What is John's leave balance?
```

### Leave assessment

```http
POST /leave-assessment
Content-Type: application/json

{
  "message": "John wants 5 days of annual leave from 2026-11-10 to 2026-11-14"
}
```

### Knowledge-base Q&A

```http
GET /knowledge/ask?question=How many annual leave days do full-time employees get?
```

## Swagger / OpenAPI

The project exposes Swagger UI for API inspection:

```text
http://localhost:8080/swagger-ui/index.html
```

## Example responses

### `/leave`

```json
{
  "employee": "John",
  "leaveType": "annual",
  "startDate": "2026-11-10",
  "endDate": "2026-11-12",
  "notes": "unknown"
}
```

### `/knowledge/ask`

```text
Full-time employees receive 18 annual leave days per year.
```

## Notes

- Demo data is intentionally simple and in-memory.
- Employee balances are mocked in `EmployeeService`.
- The vector store is initialized with a few sample company documents in `KnowledgeConfig`.
- The application is designed to demonstrate AI patterns and is not production-ready for real HR systems without secure data sources and validation.

## Useful commands

```bash
./mvnw clean test
./mvnw package
```

## License

This project is for learning and demonstration purposes.
