# DoDone Server (Todo List App)

A simple API server for Todo List application built with Java Spring Boot
along with libraries like Lombok and JPA connectivity to a MySQL database
hosted on a docker container. 

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Technologies](#technologies)


## Features

- General operations for creating, deleting, editing tasks.
- Global exception handling,
- Email service for notifications

## Installation

To get started with the Todo List app, follow these steps:

### 1. Install the application

```bash
git clone https://github.com/KodeRad/dodone-server.git
cd dodone-back
```

### 2. Set up a database
   
   a. install docker if needed: https://docs.docker.com/engine/install/

   b. get a mysql docker image:

```bash

docker pull mysql

```

c. start a MySQL container:

```bash
docker run -d --name dodone-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dodone -e MYSQL_USER=dodone_user -e MYSQL_PASSWORD=dodone_password -p 3306:3306 mysql
```

d. confrigure database connection in application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/dodone
spring.datasource.username=dodone_user
spring.datasource.password=dodone_password
```

### 3. Run the application

## Technologies

Java Spring Boot
JPA
MySQL Database on Docker
Lombok
Docker
Spring Mail (for email service)
