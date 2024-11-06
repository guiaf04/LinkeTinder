# LinkeTinder

---

## Overview

**LinkeTinder** is a unique platform designed to blend a professional networking environment with the anonymity and match-based logic of popular dating applications. The project aims to facilitate professional connections between candidates and companies in a discreet and effective way, allowing users to explore career opportunities and find talent without disclosing personal details upfront.

This project is developed with a REST API approach using a custom backend architecture without frameworks, facilitating future transitions to framework-based development. The backend is implemented in **Groovy** with **Gradle** as the build tool, and **Apache Tomcat** is utilized as the server. **PostgreSQL** is used for database management, ensuring robust and scalable data storage.

## Features
- **Anonymous professional matching** for both candidates and companies.
- Structured with MVCS architecture, including **DTOs (Data Transfer Objects)** and **DAOs (Data Access Objects)** to manage data entities such as candidates, companies, jobs, and skills.
- **REST API** designed with servlets in Java, ensuring modular and scalable endpoints.
- **Constantly evolving architecture** with a roadmap for future migration to frameworks as the knowledge base expands.

## Project Structure
- **Frontend:** TypeScript, HTML, and CSS
- **Backend:** Groovy, utilizing Apache Tomcat for server management and Gradle for dependency management
- **Database:** PostgreSQL
- **Architecture:** MVCS with DTOs and DAOs for each main data entity (candidates, companies, jobs, and skills)

## Dependencies

The following dependencies are required for the backend:
- **Groovy** (latest stable version)
- **Gradle** (for project build and dependency management)
- **Apache Tomcat** (server for running servlets)
- **PostgreSQL JDBC Driver** (for database connectivity)

## Installation Guide

### Installing Groovy
1. **Download and Install Groovy:**
    - Visit the [Groovy official website](https://groovy-lang.org/) and download the latest stable version.
    - Follow the installation instructions for your operating system.

2. **Verify Installation:**
    - Open a terminal and run `groovy -v` to confirm the installation.

### Installing Gradle
1. **Download and Install Gradle:**
    - Visit the [Gradle official website](https://gradle.org/install/) and download the latest stable version.
    - Follow the installation steps, which may include adding Gradle to your system’s PATH.

2. **Verify Installation:**
    - Run `gradle -v` in the terminal to confirm that Gradle is installed correctly.

### Setting Up Apache Tomcat with IntelliJ

1. **Download and Install Apache Tomcat:**
    - Visit [Apache Tomcat’s official website](https://tomcat.apache.org/) and download the latest stable version.
    - Follow the installation instructions for your operating system.

2. **Configure Tomcat in IntelliJ:**
    - Open your project in IntelliJ.
    - Go to `File` > `Project Structure` > `Modules` > `Dependencies` and add the necessary libraries for **Servlets** and **JDBC**.
    - Go to `Run` > `Edit Configurations`, add a new **Tomcat Server** configuration, and select your Tomcat installation.
    - Deploy the project by running the server from IntelliJ.

### Building the Project with Gradle and Creating the .war File
1. **Configure Gradle to Generate a .war File:**
    - Ensure that your `build.gradle` file is set up to create a `.war` file by adding the `war` plugin if it’s not already included:
      ```groovy
      apply plugin: 'war'
      ```

2. **Build the Project:**
    - Run the following command in the terminal to generate the `.war` file:
      ```bash
      gradle clean build
      ```
    - This command will create a `build/libs` directory containing the generated `.war` file.

### Deploying the .war File in Tomcat
1. **Deploy to Tomcat:**
    - Copy the generated `.war` file from `build/libs` into the `webapps` folder of your Tomcat installation.

2. **Start Tomcat:**
    - Start the Apache Tomcat server. Tomcat will automatically deploy the `.war` file, making the application accessible at `http://localhost:<port>/<application-name>`.

## REST API Endpoints

| Endpoint                | Method | Description                           |
|-------------------------|--------|---------------------------------------|
| `/api/candidates`       | GET    | Retrieves a list of candidates.       |
| `/api/candidates/{id}`  | GET    | Retrieves candidate by ID.            |
| `/api/candidates`       | POST   | Creates a new candidate profile.      |
| `/api/companies`        | GET    | Retrieves a list of companies.        |
| `/api/companies/{id}`   | GET    | Retrieves company by ID.              |
| `/api/companies`        | POST   | Creates a new company profile.        |
| `/api/jobs`             | GET    | Retrieves job listings.               |
| `/api/jobs/{id}`        | GET    | Retrieves job details by ID.          |
| `/api/jobs`             | POST   | Creates a new job posting.            |
| `/api/skills`           | GET    | Retrieves a list of skills.           |
| `/api/skills/{id}`      | GET    | Retrieves skill by ID.                |
| `/api/skills`           | POST   | Adds a new skill to the database.     |

## Future Development

LinkeTinder is a project under active development, designed to evolve as new knowledge and techniques are acquired. The goal is to transform this foundational application into a robust system that leverages frameworks and modern software development practices. The current structure, based on MVCS with dedicated DTOs and DAOs, facilitates seamless transitions and modular improvements over time.

--- 

