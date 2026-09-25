# QuickChat - Part 1 (PROG5121 POE)

## Project Overview
QuickChat Part 1 is a Java-based console application developed for the **PROG5121 (Programming 1A)** Portfolio of Evidence (POE). The application serves as the core authentication module for the QuickChat messaging system, establishing a secure baseline for user registration, input validation, and user authentication.

The architecture emphasizes object-oriented programming (OOP) principles and a clean separation of concerns:
* **Business & Validation Logic (`Login.java`)**: Encapsulates data validation, regular expressions, and registration/authentication operations.
* **User Interface & Execution (`QuickChat_Part1.java`)**: Manages the interactive terminal environment, handling console inputs and outputs for the end user.
* **Automated Quality Assurance (`LoginTest.java`)**: Leverages JUnit 5 to continuously test validation boundary conditions and edge cases against module rubric standards.

---

## Detailed System Features & Validation Logic

### 1. Username Format Verification
* **Condition**: Usernames must contain an underscore character (`_`) and cannot exceed 5 characters in total length.
* **Implementation**: Standard String operations inspect string length and character presence before persisting user data.

### 2. Password Complexity Enforcement
* **Condition**: Passwords must be at least 8 characters long and contain at least one uppercase letter, one numerical digit, and one special character (e.g., `@`, `#`, `&`).
* **Implementation**: Iterative character parsing verifies that all structural security criteria are met before user creation.

### 3. South African Phone Number Validation
* **Condition**: Cell phone numbers must strictly follow the international E.164 standard for South Africa (`+27` country code followed by 9 numerical digits).
* **Implementation**: Validated using Regular Expressions (`^\+27[0-9]{9}$`) to ensure data consistency and prevent invalid string entry.

### 4. Interactive Console Flow & Authentication
* Prompts users for personal information (First Name, Last Name, Cell Number) and desired credentials during setup.
* Generates clear feedback messages indicating whether registration succeeded or failed.
* Verifies credentials during login against stored user variables and displays formatted welcome or failure prompts.

---

## Technical Stack & Dependencies

* **Programming Language**: Java (JDK 17 or higher)
* **Build System**: Apache Maven
* **Development Environment**: Apache NetBeans IDE
* **Testing Framework**: JUnit 5 (Jupiter API)
* **Version Control System**: Git & GitHub

---

## Repository Structure

```text
QuickChart_Part1/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/student/quickchat_part1/
│   │           ├── Login.java               # Core business and validation logic
│   │           └── QuickChat_Part1.java     # Main interactive console application
│   └── test/
│       └── java/
│           └── com/student/quickchat_part1/
│               └── LoginTest.java           # Automated JUnit 5 test suite
├── pom.xml                                  # Maven dependencies and build configuration
└── README.md                                # Project documentation

