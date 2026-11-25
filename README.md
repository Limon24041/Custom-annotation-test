# CampusBoot

## Project Description

CampusBoot is a simple Java-based mini framework developed to demonstrate some fundamental concepts of framework development, including component registration, dependency injection, validation, and routing.

The project demonstrates how annotations and reflection can be used to manage components and handle basic application functionality.

## Features

- **@Component:** Registers components in the application.
- **@Value:** Injects values into fields.
- **@Range:** Validates whether a value is within a specified range.
- **@Route:** Defines application routes.
- **Container:** Manages component registration and dependency injection.
- **Validator:** Performs validation of field values.
- **Router:** Handles route requests and displays the corresponding responses.

## Technologies Used

- Java
- IntelliJ IDEA
- Java Annotations
- Java Reflection

## Project Structure

The project contains the following main components:

- Container
- Validator
- Router
- GradeController
- ReportPrinter
- Student
- Main

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Make sure the JDK is configured.
4. Open the `Main` class.
5. Run the `main()` method.
6. Check the output in the console.

## Sample Output

The following output demonstrates validation and routing functionality.

### Console Output

```text
VIOLATION: semester - Value out of range (actual: 15)

--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown

Process finished with exit code 0
```

### Output Screenshot

![CampusBoot Console Output](screenshots/campusboot-output.png)

## Submitted By

**Name:** Limon Hasan  
**Student ID:** IT-24041  
**Department:** Information and Communication Technology (ICT)  
**University:** Mawlana Bhashani Science and Technology University (MBSTU)  
**Session:** 2023–2024  
**Year:** 3rd Year  
**Semester:** 1st Semester
