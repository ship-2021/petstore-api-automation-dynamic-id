# PetStore API Automation Framework

## Overview

This is a **Cucumber + Java + RestAssured** API automation framework for **Swagger PetStore**.
It automates **CRUD operations**, **schema validation**, **dynamic test data**, **error handling**, and **resource cleanup**.

---

## Framework Structure

```
Feature Files (*.feature)
        │
        ▼
Step Definitions (PetSteps.java)
        │
        ▼
Utilities / Helpers
 ├── ApiUtils.java (API calls & validations)
 ├── DataGenerator.java (Dynamic test data)
 └── ConfigManager.java (Base URL & API key)
        │
        ▼
Test Execution → Cucumber Reports (HTML/JSON)
```

---

## Key Features

* **CRUD Testing**: Create, Retrieve, Update, Delete Pets
* **Error Handling**: Simulate invalid operations
* **Dynamic Data**: Unique Pet IDs per test run
* **Schema Validation**: JSON response validation
* **Reusable & Modular**: Utility classes and reusable methods
* **Cleanup**: Automatically deletes created pets

---

## Setup Instructions

1. **Clone Repository**

```bash
git clone <repo-url>
cd petstore-api-automation
```

2. **Install Dependencies**

```bash
mvn clean install
```

3. **Configure Environment (optional)**

```java
// ConfigManager.java
private static final String BASE_URL = "https://petstore.swagger.io/v2";
private static final String API_KEY = "special-key";
```

4. **Run Tests**

```bash
mvn clean test
```

5. **View Report**

```
target/cucumber-report.html
```

---

## Example Test Scenarios

* **Create & Verify Pet** → POST `/pet` → GET `/pet/{id}`
* **Update Pet Details** → PUT `/pet` → GET `/pet/{id}`
* **Find Pets by Status** → GET `/pet/findByStatus?status=available`
* **Delete Pet** → DELETE `/pet/{id}`
* **Error Handling** → GET/DELETE invalid Pet → Validate 404

---

## Execution Command

```bash
mvn clean test
```

* HTML Report: `target/cucumber-report.html`
* JSON Report: `target/cucumber-report.json`

---

## References

* Swagger Petstore API: [https://petstore.swagger.io/](https://petstore.swagger.io/)
* Cucumber Docs: [https://cucumber.io/docs](https://cucumber.io/docs)
* RestAssured Docs: [https://rest-assured.io/](https://rest-assured.io/)
