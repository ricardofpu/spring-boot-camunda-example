# Spring Boot and Camunda Engine example
[![Build Status](https://travis-ci.org/ricardofpu/spring-boot-camunda-example.svg?branch=master)](https://travis-ci.org/ricardofpu/spring-boot-camunda-example)
[![codecov](https://codecov.io/gh/ricardofpu/spring-boot-camunda-example/branch/master/graph/badge.svg)](https://codecov.io/gh/ricardofpu/spring-boot-camunda-example)

This quickstart example let's show how to build Spring Boot web application using Camunda to workflow orchestration.

## Introduction

Sometimes, we need an orchestration engine in our microservice architeture to handle workflows. Camunda is an opensource framework writen in java for Business Process Management. This example demonstrates how to build a Spring Boot application with embedded Camunda enginer

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

![MODULES](https://github.com/ricardofpu/camunda-example/blob/master/docs/image/camunda-example-modules.png)

Description:

* API - Our REST API's, requests and responses
* Web - Web context that will handle requests
* Domain - Our entity classes and interfaces that difine contracts with other modules
* Workflow - Camunda tasks
* Infrastructure - Util classes
* Repository - Persitence layer, JPA Repositories and Liquibase configuration
* Exception Handler - Custom exception handler

## Example Scenario

We will use Camunda to orchestrate a transference balance workflow. Basically, any customer can transference balance to another customer, but how it works? Well, the user will choose any destination customer and define an value. However,after receiving the transfer request, we will start a workflow that will validate and complete it for us. So using Camunda Modeler, we create the workflow:

### Transference Workflow
![TRANSFERENCE-WORKFLOW](https://github.com/ricardofpu/camunda-example/blob/master/docs/image/transference-workflow.png)

### Execute Transference Flow
![EXECUTE-TRANSFERENCE-FLOW](https://github.com/ricardofpu/camunda-example/blob/master/docs/image/execute-transference-flow.png)

