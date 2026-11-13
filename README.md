# QuickChat - Part 1, Part 2 & Part 3 (PROG5121 POE)

## Author & Academic Information
- **Lecturer**: Mr Colly
- **Author**: Ayanda Sosibo
- **Student Number**: ST10482388
- **Module**: PROG5121
- **Assessment**: Portfolio of Evidence (POE) - Part 1, Part 2 & Part 3

## Project Overview
QuickChat is a Java console-based application developed for the PROG5121 (Programming 1A) Portfolio of Evidence (POE). It combines user registration and authentication (Part 1), message creation, hashing, and JSON persistence (Part 2), and dynamic stored-message array management, search, deletion, and reporting (Part 3).

The codebase follows object-oriented programming (OOP) principles and separation of concerns:
- **Business & Validation Logic (`Login.java`)**: Handles username, password, and South African cell phone validation plus registration and authentication flows.
- **Message Management Logic (`Message.java`)**: Manages message payload creation, ID generation, cell validation, hash formatting, JSON serialization/loading, parallel collections, search, deletion, and reporting.
- **User Interface & Execution (`QuickChat_Part1.java`)**: Drives the interactive console workflow for registration, login, main menu navigation, message sending, and the Stored Messages sub-menu.
- **Automated Quality Assurance (`LoginTest.java` & `MessageTest.java`)**: Uses JUnit 5 via Maven Surefire to verify validation rules, message actions, array population, JSON loading, search, hash deletion, and reporting.

## Detailed System Features & Validation Logic

### Part 1: User Authentication & Validation
1. **Username Format Verification**:
   - **Condition**: Must contain an underscore (`_`) and not exceed 5 characters in total length.
   - **Implementation**: Checked using string length and character presence validation.
2. **Password Complexity Enforcement**:
   - **Condition**: At least 8 characters long with at least one uppercase letter, one digit, and one special character.
   - **Implementation**: Verified through iterative character checks.
3. **South African Phone Number Validation**:
   - **Condition**: Follows E.164 format for South Africa (`+27` followed by 9 digits).
   - **Implementation**: Validated with regular expression `^\+27[0-9]{9}$`.
4. **Interactive Console Flow & Authentication**:
   - Collects user details, enforces registration validation loops, and authenticates login credentials.

### Part 2: Messaging, Hashing & JSON Storage
1. **Interactive Main Menu**:
   - Displays `"Welcome to QuickChat."` after login.
   - Supports main menu options `1) Send Messages`, `2) Show recently sent messages`, `3) Quit`, and `4) Stored Messages`.
2. **Message Fields & Rules**:
   - **Unique Message ID**: Generates a 10-digit tracking ID.
   - **Recipient Cell**: Validates `+27` international format.
   - **Message Length**: Enforces a maximum of 250 characters.
   - **Message Hash**: Builds an uppercase hash from the first two ID digits, message number, and first/last words (e.g., `00:0:HITONIGHT?`).
3. **Message Actions & JSON Persistence**:
   - Supports Send (`1`), Disregard (`2`), and Store (`3`), appending stored records to `messages.json`.

### Part 3: Stored Messages, Search, Deletion & Reporting
1. **Dynamic Array / List Management**:
   - Tracks sent messages, stored messages, disregarded messages, message IDs, message hashes, senders, recipients, and source markers in `Message.java`.
   - Loads stored message records from `messages.json` via `loadStoredMessagesFromJson(String filePath)`.
2. **Stored Messages Sub-Menu (Option 4)**:
   - `a)` Display sender and recipient of all stored messages
   - `b)` Display longest stored/sent message
   - `c)` Search for a message ID and display recipient and message
   - `d)` Search messages by recipient cell number
   - `e)` Delete a message using its message hash
   - `f)` Display full message report
   - `g)` Return to Main Menu

## Technical Stack & Dependencies
- **Programming Language**: Java (JDK 26)
- **Build System**: Apache Maven (`pom.xml`)
- **Development Environment**: Apache NetBeans IDE
- **Testing Framework**: JUnit 5 (Jupiter API)
- **CI/CD Automation**: GitHub Actions (`.github/workflows/testJava.yml`)
- **Version Control System**: Git & GitHub (Feature Branch `KhanbanTasks-Part3` merged into `master`)

## Build & Test Instructions
Run a full clean build and JUnit test execution from the project root:
```bash
mvn clean install

## Academic Refences
JUnit Team, (2024). JUnit 5 User Guide. Available at: https://junit.org/junit5/current/user-guide/ [Accessed 25 September 2026].
Oracle, (2024). Java SE Documentation: Pattern Matching, Regular Expressions, and File I/O. Oracle Corporation. Available at: https://docs.oracle.com/en/java/javase/ [Accessed 25 September 2026].
OWASP Foundation, (2023). OWASP Validation Regex Repository. Available at: https://owasp.org/www-community/OWASP_Validation_Regex_Repository [Accessed 25 September 2026].

## Repository Structure
QuickChat_Part1/
├── .github/
│   └── workflows/
│       └── testJava.yml                     # Automated CI/CD JUnit test pipeline
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/student/quickchat_part1/
│   │           ├── Login.java               # Part 1 authentication & validation logic
│   │           ├── Message.java             # Part 2 & Part 3 message logic, hashing, JSON & collections
│   │           └── QuickChat_Part1.java     # Main interactive console application & sub-menus
│   └── test/
│       └── java/
│           └── com/student/quickchat_part1/
│               ├── LoginTest.java           # Part 1 JUnit 5 test suite (7 tests)
│               └── MessageTest.java         # Part 2 & Part 3 JUnit 5 test suite (15 tests)
├── messages.json                            # Local JSON storage output
├── pom.xml                                  # Maven dependencies and build configuration
└── README.md                                # Project documentation and references
