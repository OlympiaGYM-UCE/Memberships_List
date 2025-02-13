# Memberships_List

A Java-based membership management system for OlympiaGYM-UCE, featuring WhatsApp SMS integration.

## Project Overview

This application manages gym memberships with the following features:
- Membership management system
- WhatsApp SMS integration for notifications
- MVC architecture
- RESTful API endpoints

## Technology Stack

- Java
- Spring Framework
- Maven
- WhatsApp API Integration

## Project Structure

```
Memberships_List/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── gym/
│                   └── olympia/
│                       ├── controller/     # REST controllers
│                       ├── entity/         # Data models
│                       ├── repository/     # Data access layer
│                       ├── service/        # Business logic
│                       ├── OlympiaApplication.java
│                       └── ServletInitializer.java
├── resources/          # Application resources
├── test/              # Test files
├── Dockerfile         # Container configuration
├── mvnw              # Maven wrapper
├── mvnw.cmd          # Maven wrapper for Windows
└── pom.xml           # Project dependencies
```

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- Docker (optional)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/your-username/Memberships_List.git
cd Memberships_List
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

## Docker Support

Build the Docker image:
```bash
docker build -t olympiagym/memberships-list .
```

Run the container:
```bash
docker run -p 8080:8080 olympiagym/memberships-list
```

## Features

- Membership management
- WhatsApp SMS notifications
- RESTful API endpoints
- Containerized deployment support

## Development

The project follows a standard MVC architecture:
- Controllers: Handle HTTP requests
- Services: Implement business logic
- Repositories: Manage data access
- Entities: Define data models

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
