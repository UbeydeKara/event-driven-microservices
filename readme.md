# Event Driven Microservices

![event-driven-microservices](https://ubeydekara.github.io/microservice-diagram.svg)

Event-driven microservices provide a way to access both historical and new data in the form of an append-only immutable log of events. It's also an excellent solution when we need to provide multiple departments and teams with access to the same set of data in a consistent way.

The architecture has two key components: event producers and event consumers. A producer publishes and pushes the events to consumers. Producer services and consumer services are decoupled, which allows them to be scaled, updated, and deployed independently.

## Architecture

Our microservices-based system consists of the following modules:
- **gateway-service** - a flexible way of routing requests based on a number of criteria, as well as focuses on cross-cutting concerns such as security, resiliency, and monitoring.
- **discovery-service** - discovery client retrieves a list of all connected peers in a service registry, and makes all further requests to other services through a load-balancing algorithm.
- **order-service** - a service responsible for creating orders. It communicates with both stock-service and email-service.
- **stock-service** - when an order is created, it listens to kafka topic and performs the necessary stock transactions. It communicates with order-service.
- **email-service** - when an order is created, it listens to kafka topic and sends the created order information to the customer as an email. It communicates with order-service.
- **kafka-config** - a module containing the *Producer* and *Consumer* configurations.

### Usage

Build the apps with images:
```shell
$ mvn clean package -Pbuild-image
```

Then run all the containers with `docker-compose`:
```shell
$ docker-compose up
```

### TODO
- Config Server
- Kafka Streams
- Kubernetes
- JUnit Testing with Mockito