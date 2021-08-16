# Todoapp
Simple todo starter application using minimal features of spring boot

## Synopsis

This is a simple todo application. It's a minimal starter application with minimal implementation of features.

My goal was to implement following features with minimal implementation for starters and extend it ahead as per requirement.

I have integrated following features which can be used out of the box :

1. Full fledge Restful Service prototype using RestController, SpringData, MySQL.
2. Caching using ehcache and guavacache.
3. Transaction Management.
4. Managing Asynchronous Process.
5. Enviroment specific configuration.
6. Securing the services using Redis session.
7. Lots of actuators customization.
8. JUnit and Mockito testing.
8. A lot to be added in near future.


## Motivation

The reason I created the project was to have a minimal skeleton to build a spring boot project with best practice. I know when I will be commiting my first commit it will be far from best practice but with time I will update and make it best for me or anyone who wants to use it.

## Installation and Getting Started
```
  $ mvn clean package
  java -jar target/todoapp-0.0.1-SNAPSHOT.jar   
  
  or Alternately we can type and hit,
  $ mvn spring-boot:run
```
This is will start your application embedded tomcat server on port 8080

## API Reference

Todo API/Services are accessible at :
```
localhost:8080/api/todo/**
```

> ** Security Config for ROLE_USER : UserName- user | Password - password

and Actuators Services are accessible at :
```
localhost:8080/actuators/**
```
> ** Security Config for ROLE_ADMIN : UserName- admin | Password - password

## Tests

Tests are in their test directory. It includes very minimal testcases using Mockito and JUnit

## Contributors

[Nasruddin](https://twitter.com/iam_nasir)

