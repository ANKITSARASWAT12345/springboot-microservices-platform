# springboot-microservices-platform
This repository demonstrates a production-inspired microservices architecture built with Spring Boot and Spring Cloud. The project focuses on scalable backend design, service discovery, centralized configuration, secure inter-service communication, API Gateway, resilience patterns, and distributed system concepts commonly used in enterprise applications.


# Project Architecture

                   Client
                      │
             API Gateway
                      │
      ------------------------------
      │            │              │
 User Service  Hotel Service  Rating Service
      │            │              │
      ------------------------------
                OpenFeign
                      │
           Eureka Service Registry
                      │
            Config Server
                      │
         Spring Security (Okta)
                      │
      Circuit Breaker | Retry | Rate Limiting


# Technologies Section


- Java 21
- Spring Boot
- Spring Cloud
- Eureka Server
- Spring Cloud Gateway
- Config Server
- OpenFeign
- Resilience4j
- Spring Security
- OAuth2 / Okta
- Rate Limiting
- Circuit Breaker
- Retry
- REST APIs
- Maven
- MySQL
- PostgresSQL
- MongoDB


# Features

✔ Spring Boot Microservices

✔ Eureka Service Discovery

✔ API Gateway

✔ Config Server

✔ Centralized Configuration

✔ OpenFeign Client

✔ Inter-Service Communication

✔ Circuit Breaker

✔ Retry Mechanism

✔ Rate Limiting

✔ Spring Security

✔ OAuth2 Authentication (Okta)

✔ Global Exception Handling

✔ RESTful APIs

✔ Load Balancing

✔ Externalized Configuration

✔ Fault Tolerance

✔ Distributed System Design
