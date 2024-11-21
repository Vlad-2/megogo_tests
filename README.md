# Megogo Tests Automation

## 📖 Description
This project is designed for automated testing and CI/CD pipeline setup using **Jenkins**, **Docker**, and **Maven**. The repository contains all necessary configurations for easy deployment and usage.

---

## ⚙️ Prerequisites
Before you begin, make sure the following tools are installed on your system:
- [Docker](https://www.docker.com/) - For containerized environments.
- [Java (JDK 17)](https://www.oracle.com/java/technologies/javase-downloads.html) - Required for running the project.
- [Maven](https://maven.apache.org/) - For building and running tests.
- [Allure](https://docs.qameta.io/allure/) - For test reporting (optional, if used).

## ⚙️To work with Docker, run the following commands:
```bash
docker build -t custom-jenkins .
```
```bash
docker run -d -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home --name custom-jenkins custom-jenkins
```
## ⚙️ Jenkins
Host
```bash
http://localhost:8080/
```
Credential
```bash
login = admin
password = admin
```

