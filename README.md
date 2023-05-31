# Demo2

This project aims to develop a system for organizing and managing timetables for modules at the School of Computing and Mathematical Sciences. The system allows tracking of convenors, the modules they teach, and the sessions delivered in each module.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [Convenor Endpoints](#convenor-endpoints)
  - [Module Endpoints](#module-endpoints)
  - [Session Endpoints](#session-endpoints)

## Description

The Module Timetable Management System enables the School of Computing and Mathematical Sciences to efficiently manage and organize module timetables. It provides functionalities to track convenors, modules, and sessions related to the modules.

## Features

- Manage convenors, modules, and sessions for module timetables.
- Create, retrieve, update, and delete convenors, modules, and sessions.
- List all convenors and their associated modules.
- List all modules and their sessions.
- Filter sessions by convenor and module.
- Ensure data integrity through relationships between resources.
- Automatic deletion of related resources when appropriate.

## Installation

To install and run the Module Timetable Management System, follow these steps:

1. Clone the repository from GitHub: https://github.com/BatuhanErTo/demo2.git 
2. Navigate to the project directory: cd demo2 
3. Install the required dependencies using Maven: mvn install
4. Start the application: mvn spring-boot:run -Dserver.port=8081
5. The application should now be running and accessible at `http://localhost:8081`.

## Usage

Once the application is running, you can interact with it using the provided API endpoints. Detailed information about the available endpoints is provided in the [API Endpoints](#api-endpoints) section below.

Please refer to the API documentation or the source code for more detailed usage instructions and request/response formats.

## API Endpoints

### Convenor Endpoints

- **List all convenors** (GET /convenors) - Retrieves a list of all convenors.
- **Create a convenor** (POST /convenors) - Creates a new convenor.
- **Retrieve a specific convenor** (GET /convenors/{id}) - Retrieves details of a specific convenor.
- **Update a specific convenor** (PUT /convenors/{id}) - Updates information for a specific convenor.
- **Delete a specific convenor** (DELETE /convenors/{id}) - Deletes a specific convenor.
- **List all modules taught by a convenor** (GET /convenors/{id}/modules) - Retrieves a list of all modules taught by a specific convenor.
- **Add a module to a convenor** (POST /convenors/{id}/addModule) - Adds a module to a specific convenor.

### Module Endpoints

- **List all modules** (GET /modules) - Retrieves a list of all modules.
- **Create a module** (POST /modules) - Creates a new module.
- **Retrieve a specific module** (GET /modules/{code}) - Retrieves details of a specific module.
- **Update a specific module** (PATCH /modules/{code}) - Updates information for a specific module.
- **Delete a specific module** (DELETE /modules/{code}) - Deletes a specific module.
- **List all sessions in a module** (GET /modules/{code}/sessions) - Retrieves a list of all sessions in a specific module.
- **Create a session in a module** (POST /modules/{code}/sessions) - Creates a new session in a specific module.
- **Retrieve a specific session in a module** (GET /modules/{code}/sessions/{id}) - Retrieves details of a specific session in a module.
- **Update a specific session in a module** (PUT /modules/{code}/sessions/{id}) - Updates information for a specific session in a module.
- **Partially update a specific session in a module** (PATCH /modules/{code}/sessions/{id}) - Partially updates information for a specific session in a module.
- **Delete a specific session in a module** (DELETE /modules/{code}/sessions/{id}) - Deletes a specific session in a module.

### Session Endpoints

- **Delete all sessions** (DELETE /sessions) - Deletes all sessions.
- **List all sessions** (GET /sessions) - Retrieves a list of all sessions. Can be filtered by convenor ID and/or module code using query parameters.
- Filter by convenor: `/sessions?convenor={id}`
- Filter by module: `/sessions?module={code}`
- Filter by both: `/sessions?convenor={id}&module={code}`
