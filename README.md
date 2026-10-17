# QuickChat - Part 1 & Part 2 (PROG5121 POE)

## Project Overview
QuickChat is a robust Java console-based application developed for the **PROG5121 (Programming 1A)** Portfolio of Evidence (POE). The application provides a secure user authentication portal (Part 1) integrated with an interactive message management, formatting, and JSON storage system (Part 2).

The architecture adheres strictly to Object-Oriented Programming (OOP) principles, separating business logic, user interface controllers, data storage handlers, and automated test suites.

---

## Key Features & Functionality

### Part 1: User Authentication & Validation
* **Username Validation**: Ensures usernames contain an underscore (`_`) and are no more than 5 characters long.
* **Password Complexity**: Enforces passwords to be at least 8 characters long, containing a capital letter, a digit, and a special character.
* **Cell Phone Number Validation**: Validates South African phone numbers matching international E.164 standards (`+27` followed by 9 digits).
* **User Authentication**: Securely matches login credentials against registered user data.

### Part 2: Messaging, Hashing & Storage
* **Interactive Main Menu**: Allows authenticated users to navigate options to send messages, view recent messages ("Coming Soon"), or quit.
* **Message ID Generation**: Autogenerates unique 10-digit tracking IDs for each message.
* **Recipient Validation**: Ensures recipient numbers conform to international formatting standards.
* **Length Constraints**: Validates that message text does not exceed 250 characters.
* **Message Hashing**: Autogenerates an uppercase hash containing the first two digits of the Message ID, message number, and the first and last words of the text (e.g., `00:0:HITONIGHT`).
* **Message Actions**: Supports sending, disregarding/deleting, or storing messages.
* **JSON Persistence**: Automatically serializes and saves stored messages locally into `messages.json`.

---

## Technical Stack & Dependencies
* **Programming Language**: Java (JDK 26)
* **Build Tool**: Apache Maven (`pom.xml`)
* **Development Environment**: Apache NetBeans IDE
* **Testing Framework**: JUnit 5 (Jupiter API)
* **CI/CD Automation**: GitHub Actions (`.github/workflows/testJava.yml`)
* **Version Control**: Git & GitHub (Feature Branching & Pull Requests)

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
│   │           ├── Message.java             # Part 2 message rules, hashing & JSON storage
│   │           └── QuickChat_Part1.java     # Main interactive console application runner
│   └── test/
│       └── java/
│           └── com/student/quickchat_part1/
│               ├── LoginTest.java           # Part 1 JUnit 5 test suite
│               └── MessageTest.java         # Part 2 JUnit 5 test suite
├── messages.json                            # Local JSON storage output for stored messages
├── pom.xml                                  # Maven dependencies and build configuration
└── README.md                                # Project documentation and references
