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