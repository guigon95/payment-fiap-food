# payment-fiap-food

## Description

This project is a simple API to simulate a payment system for a food delivery application. It was developed as the tech challenger for the FIAP MBA in Software Architecture.

## Padrão Saga:

Escolhemos o padrão Saga Coreografado por ser simples e escalável. Isso significa que cada microserviço sabe o que fazer sem precisar de um controle central, o que torna mais fácil de implementar e menos complexo. Além disso, cada serviço pode ser aumentado ou diminuído sem afetar os outros, o que ajuda a tornar o sistema mais robusto.
A coreografia não precisa de um controle central, então não tem um único ponto onde algo pode dar errado, o que ajuda a manter o sistema mais confiável e disponível. Isso é muito importante em arquiteturas que usam microserviços, onde uma falha em um serviço pode causar problemas grandes.
Por essas razões, o padrão Saga Coreografado foi a nossa escolha para o Sistema FIAP-FOOD, por ser eficiente, escalável e resistente, sem precisar de um controle central que pudesse ser um único ponto de falha

## Prerequisites:
- Apache Maven 3.+
- JDK 21
- Docker 20+
- Docker Compose 1.22.+

## Start application in Docker container in Docker:
```
    docker-compose up -d
```

## Stop all applications in Docker containers:
```
    docker-compose stop
```

## Start application in Docker container in Docker and rebuild service image:
```
    docker-compose up --build --force-recreate --no-deps -d 
```

## Swagger documentation:
```
    http://localhost:8080/swagger-ui.html
```

## Swagger
![img_1.png](img_1.png)

## Architecture
![payment.png](payment.png)

## Checkout Cycle Forms
![img.png](img.png)
