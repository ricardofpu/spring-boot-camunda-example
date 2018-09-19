# Spring Boot and Camunda Engine example
This quickstart example let's show how to build Spring Boot web application using Camunda to workflow orchestration.

## Introduction

Sometimes, we need an orchestration engine in our microservice architeture to handle workflows. The Camunda is a opensource framework writen in java for Business Process Management. This example demonstrates how to build a Spring Boot application with embedded Camunda enginer

## Project Frameworks

- Kotlin
- Spring Boot
- Maven
- PostgreSQL
- Docker
- Camunda BPM
- Camunda Modeler
- Liquibase

We will build a simple application to simulate a bank account management with simple operations:

* CRUD Customer
* Create account
* Do debits and credits
* Do transference

That's right. This is the modules structure:

![DER](https://github.com/ricardofpu/camunda-example/blob/master/docs/diagram/camunda-example-modules.png)


![DER](https://github.com/ricardofpu/camunda-example/blob/master/docs/diagram/camunda-example-der-V2.png)

