# DevOps Final Project: Todo Web Application

## Description

The project integrates various DevOps tools and practices, such as **Docker**, **Jenkins**, **Puppet**, **Git**, **Maven**, **Grafana**, and **Graphite**, to automate the build, deployment, and monitoring processes. It serves as a practical example of implementing a CI/CD pipeline and monitoring system performance for a Java-based web application.

## Features
- **Todo Application**: A simple web app to manage tasks (add tasks and mark them as completed).
- **Spring Boot Backend**: Built with Java and Spring Boot, using Thymeleaf for the frontend.
- **Unit Testing**: Backend testing with JUnit to ensure functionality.
- **Containerization**: Dockerized application with a custom Dockerfile.
- **CI/CD Pipeline**: Automated builds and deployments using Jenkins, triggered by GitHub commits.
- **Configuration Management**: Puppet for managing the application as a service.
- **Monitoring**: System stats (e.g., CPU usage) monitored using Grafana, Graphite, and collectd.
- **Version Control**: Code managed and versioned using Git and hosted on GitHub.

## Technologies Used
- **Backend**: Java, Spring Boot, Thymeleaf
- **Frontend**: HTML, Thymeleaf
- **Testing**: JUnit
- **Build Tool**: Maven
- **Containerization**: Docker, Docker Compose
- **CI/CD**: Jenkins
- **Configuration Management**: Puppet
- **Monitoring**: Grafana, Graphite, collectd
- **Version Control**: Git, GitHub
- **Operating System**: Ubuntu

## Project Structure
- `src/main/java/com/devops/coreproject/`: Contains Java source code for the Todo application (TodoManager, TodoController).
- `src/main/resources/templates/`: Thymeleaf templates (e.g., `index.html`) for the web interface.
- `src/test/java/`: JUnit tests for the backend.
- `application.properties`: Spring Boot configuration for Thymeleaf.
- `pom.xml`: Maven configuration for dependencies and build.
- `Dockerfile`: Docker configuration to containerize the application.
- `deploy.pp`: Puppet manifest for configuring the application as a service.
- `docker-compose.yml`: Docker Compose file for running Grafana and Graphite.
- `README.md`: This file.

## Prerequisites
- **Java**: JDK 17
- **Maven**: For building the project
- **Docker**: For containerization
- **Jenkins**: For CI/CD pipeline
- **Puppet**: For configuration management
- **Git**: For version control
- **Grafana & Graphite**: For monitoring
- **Ubuntu**: The project was developed and tested on an Ubuntu environment

## Setup and Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/VishalShekha/The-Todo-App.git
   cd The-Todo-App
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application Locally**:
   ```bash
   java -jar target/coreproject-0.0.1-SNAPSHOT.jar
   ```
   - Access the application at `http://localhost:8080`.

4. **Dockerize the Application**:
   - Build the Docker image:
     ```bash
     docker build -t todo-app .
     ```
   - Run the Docker container:
     ```bash
     docker run -p 8081:8080 todo-app
     ```
   - Access the application at `http://localhost:8081`.

5. **Set Up Jenkins**:
   - Configure Jenkins to poll the GitHub repository (`https://github.com/VishalShekha/The-Todo-App`) every 5 minutes.
   - Set up a pipeline to build the project using `mvn clean install` and deploy the Docker container.

6. **Apply Puppet Configuration**:
   - Run the Puppet manifest to configure the application as a service:
     ```bash
     puppet apply deploy.pp
     ```

7. **Set Up Monitoring**:
   - Use Docker Compose to run Grafana and Graphite:
     ```bash
     docker-compose up -d
     ```
   - Configure Graphite as the data source in Grafana.
   - Set up collectd to send system metrics (e.g., CPU usage) to Graphite.
   - Access Grafana's dashboard to monitor the application.

## Usage
- Open the application in a web browser at `http://localhost:8081` (or `http://localhost:8080` if running locally).
- Enter a task description and click **Add Task** to create a new task.
- Click **Mark as Completed** next to a task to update its status.
- View system metrics (e.g., CPU usage) on the Grafana dashboard.

## Contributing
This is an entry-level project created for learning purposes. Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.

## Acknowledgments
- Thanks to the open-source community for providing tools like Spring Boot, Docker, Jenkins, Puppet, Grafana, and Graphite.

