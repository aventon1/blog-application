# Blog Application

A simple Spring Boot blog application for registered users to create and edit posts.

## Getting Started

### Dependencies
- Java 22
- Spring Boot 3.3.3
- Maven

### Installation

Clone repository or download Zip from GitHub

```bash
git clone https://github.com/aventon1/blog-application.git
```

Create a database and include in ```application.properties``` file


```
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE
```

Change MySQL database username and password in ```application.properties``` file
```
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

Load application at [http://localhost:8080/](http://localhost:8080/)

## Seed Data
- Initial User data is loaded in ```UserDataLoader.java```
- Spring Simple Security Login ```user1@email.com``` / ```user2@email.com``` Password ```password```




## Project Architecture
- Model View Controller (MVC)
- Spring Boot, Spring JPA, Spring Security
- MySQL Database
- Thymeleaf
- HTML/CSS
