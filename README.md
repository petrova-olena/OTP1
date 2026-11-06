# Temperature Converter

## Overview

Temperature Converter is a Java application for converting temperatures between different measurement units. The project demonstrates object-oriented programming principles, database interaction through the DAO pattern, automated testing, and containerized deployment using Docker.

The application supports temperature conversion operations and stores conversion records for future retrieval.

Repository:
https://github.com/petrova-olena/OTP1/tree/be50f4a9d6ddfd81a47ee9cfec74da9496fdc32b

Docker Hub:
https://hub.docker.com/repository/docker/olenape/temperature-converter/general

---

## Features

- Convert temperatures between different units
- Store temperature conversion records
- Retrieve previously saved records
- Database integration
- Graphical User Interface (GUI)
- Unit testing with JUnit
- Dockerized deployment

---

## Technologies Used

- Java
- Maven
- JUnit 5
- MariaDB
- Jenkins
- Docker

---

## Project Architecture

The application follows a layered architecture to separate responsibilities and improve maintainability.

### Model Layer

Contains domain objects used throughout the application.

- `TemperatureRecord`
- `TemperatureUnit`

### DAO Layer

Provides access to database operations.

- `TemperatureRecordDAO`
- `TemperatureUnitDAO`

### Database Layer

Handles database connectivity.

- `DBConnection`



### Business Logic Layer

Implements temperature conversion functionality.

- `TemperatureConverter`

### Application Layer

Application entry point.

- `Main`

---

## Database Localization

The application implements a database-driven localization approach using language-specific columns within the `temperature_unit` table. This strategy was selected because the application supports a fixed set of five languages: English, Finnish, Swedish, Japanese, and Arabic.

The `temperature_unit` table stores a separate name column for each supported language:

The `temperature_unit` table stores a separate column for each supported language:

```text
temperature_unit
--------------------------------
id
name_en
name_fi
name_sv
name_ja
name_ar
symbol
```

Example data:

| id | name_en | name_fi | name_sv | name_ja | name_ar | symbol |
|----|----------|----------|----------|----------|----------|--------|
| 1 | Celsius | Celsius | Celsius | 摂氏 | سيلسيوس | C |
| 2 | Fahrenheit | Fahrenheit | Fahrenheit | 華氏 | فهرنهايت | F |

When the user changes the application language, the system selects the appropriate localized value from the database and displays it in the user interface. The `TemperatureUnit` model provides a localization method that returns the correct name according to the active locale.

This localization strategy was chosen because the application contains a very small and fixed reference dataset consisting of only two temperature units. Using separate language columns keeps the database schema simple, avoids additional translation tables and joins, and improves readability of the data. While a dedicated translation table would provide greater scalability for larger systems, the column-based approach offers a practical and efficient solution for this educational project.

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.example
│   │       ├── dao
│   │       │   ├── TemperatureRecordDAO.java
│   │       │   └── TemperatureUnitDAO.java
│   │       ├── database
│   │       │   └── DBConnection.java
│   │       ├── model
│   │       │   ├── TemperatureRecord.java
│   │       │   └── TemperatureUnit.java
│   │       ├── Main.java
│   │       └── TemperatureConverter.java
│   └── resources
│
└── test
    └── java
        └── com.example
            ├── dao
            ├── database
            │   └── DBConnectionTest.java
            ├── model
            │   ├── TemperatureRecordTest.java
            │   └── TemperatureUnitTest.java
            ├── MainTest.java
            └── TemperatureConverterTest.java
```

---

## Building the Project

Clone the repository:

```bash
git clone https://github.com/petrova-olena/OTP1.git
cd OTP1
```

Build the project:

```bash
mvn clean package
```

---

## Running Tests

Execute all tests:

```bash
mvn test
```

---

## Running the Application

After building the project:

```bash
java -jar target/temperature-converter.jar
```

---

## Docker Deployment

### Pull the image

```bash
docker pull olenape/temperature-converter:latest
```

### Run the container

```bash
docker run olenape/temperature-converter:latest
```

---

## GUI Application

The project includes a graphical user interface that allows users to:

- Enter a temperature value
- Select source and target units
- Convert temperatures instantly
- View conversion results in a user-friendly format

---

## Testing

The project contains automated unit tests covering:

- Temperature conversion logic
- Database connection functionality
- Model validation
- Application entry point functionality

Tests are implemented using JUnit.

---

## Future Improvements

Possible future enhancements include:

- Support for additional temperature scales
- Conversion history export
- Enhanced GUI design
- Input validation improvements
- Conversion statistics and analytics

---

## Author

Olena Petrova