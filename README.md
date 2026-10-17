# QuickChat - Part 1 & Part 2 (PROG5121 POE)

## Author & Academic Information
- **Author**: Ayanda Sosibo
- **Student Number**: ST10482388
- **Module**: PROG5121
- **Assessment**: Portfolio of Evidence (POE) - Part 1 & Part 2

---

## Project Overview
QuickChat is a robust Java console-based application developed for the **PROG5121 (Programming 1A)** Portfolio of Evidence (POE). The application provides a secure user authentication portal (Part 1) integrated with an interactive message management, formatting, and JSON storage system (Part 2).

The architecture emphasizes object-oriented programming (OOP) principles and a clean separation of concerns:
- **Business & Validation Logic (`Login.java`)**: Encapsulates data validation, regular expressions, and registration/authentication operations[cite: 14, 15, 16].
- **Message Management Logic (`Message.java`)**: Handles message payload creation, ID generation, cell number validation, hash formatting, action handling, and JSON storage[cite: 20, 21, 22].
- **User Interface & Execution (`QuickChat_Part1.java`)**: Manages the interactive terminal environment, handling console inputs, registration, login, and main menu navigation[cite: 19].
- **Automated Quality Assurance (`LoginTest.java` & `MessageTest.java`)**: Leverages JUnit 5 to continuously test validation boundary conditions and edge cases against module rubric standards[cite: 16, 23, 24].

---

## Detailed System Features & Validation Logic

### Part 1: User Authentication & Validation
1. **Username Format Verification**:
   - **Condition**: Usernames must contain an underscore character (`_`) and cannot exceed 5 characters in total length[cite: 14].
   - **Implementation**: Evaluated using standard String length and presence checks[cite: 16].

2. **Password Complexity Enforcement**:
   - **Condition**: Passwords must be at least 8 characters long and contain at least one uppercase letter, one numerical digit, and one special character (e.g., `@`, `#`, `&`)[cite: 14].
   - **Implementation**: Iterative character parsing verifies all structural security criteria[cite: 16].

3. **South African Phone Number Validation**:
   - **Condition**: Cell phone numbers must strictly follow the international E.164 standard for South Africa (`+27` country code followed by 9 numerical digits)[cite: 15].
   - **Implementation**: Validated using Regular Expressions (`^\+27[0-9]{9}$`)[cite: 15].

4. **Interactive Console Flow & Authentication**:
   - Prompts users for personal information and credentials during setup[cite: 14].
   - Verifies credentials during login and displays formatted status prompts[cite: 15, 16].

### Part 2: Messaging, Hashing & JSON Storage
1. **Interactive Main Menu**:
   - Displays welcome message (`"Welcome to QuickChat."`) upon successful login[cite: 19].
   - Numeric menu options: `1) Send Messages`, `2) Show recently sent messages` (`"Coming Soon."`), and `3) Quit`[cite: 19].

2. **Message Fields & Rules**:
   - **Unique Message ID**: Autogenerates a 10-digit tracking number for each message[cite: 20].
   - **Recipient Cell**: Validates that recipient cell numbers contain an international country code (`+27`)[cite: 20].
   - **Message Length**: Enforces a maximum limit of 250 characters per message[cite: 20].
   - **Message Hash**: Autogenerates an all-uppercase hash containing the first two digits of the Message ID, message number, and first/last words (e.g., `00:0:HITONIGHT`)[cite: 20].

3. **Message Actions & JSON Persistence**:
   - Supports message choices: Send (`"Message successfully sent"`), Disregard (`"Press 0 to delete the message"`), or Store (`"Message successfully stored"`)[cite: 21].
   - Automatically serializes and saves stored messages into `messages.json`[cite: 21].

---

## Technical Stack & Dependencies
- **Programming Language**: Java (JDK 26)
- **Build System**: Apache Maven (`pom.xml`)
- **Development Environment**: Apache NetBeans IDE
- **Testing Framework**: JUnit 5 (Jupiter API)
- **CI/CD Automation**: GitHub Actions (`.github/workflows/testJava.yml`)[cite: 24]
- **Version Control System**: Git & GitHub (Feature Branching & Pull Requests)[cite: 19]

---

## Academic References
- **JUnit Team**, (2024). *JUnit 5 User Guide*. Available at: <https://junit.org/junit5/current/user-guide/> [Accessed 25 September 2026].
- **Oracle**, (2024). *Java SE Documentation: Pattern Matching, Regular Expressions, and File I/O*. Oracle Corporation. Available at: <https://docs.oracle.com/en/java/javase/> [Accessed 25 September 2026].
- **OWASP Foundation**, (2023). *OWASP Validation Regex Repository*. Available at: <https://owasp.org/www-community/OWASP_Validation_Regex_Repository> [Accessed 25 September 2026].
- **Satzinger, J.W., Jackson, R.B. and Burd, S.D.**, (2016). *Systems Analysis and Design in a Changing World*. 7th ed. Boston: Cengage Learning.

---

## Repository Structure

```text
QuickChart_Part1/
├── .github/
│   └── workflows/
│       └── testJava.yml                     # Automated CI/CD JUnit test pipeline
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/student/quickchat_part1/
│   │           ├── Login.java               # Part 1 authentication & validation logic
│   │           ├── Message.java             # Part 2 message logic, hashing & JSON storage
│   │           └── QuickChat_Part1.java     # Main interactive console application
│   └── test/
│       └── java/
│           └── com/student/quickchat_part1/
│               ├── LoginTest.java           # Part 1 JUnit 5 test suite
│               └── MessageTest.java         # Part 2 JUnit 5 test suite
├── messages.json                            # Local JSON storage output
├── pom.xml                                  # Maven dependencies and build configuration
└── README.md                                # Project documentation
