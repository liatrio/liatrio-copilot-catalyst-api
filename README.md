# DevOps Knowledge Share API (DKS API)

DevOps Knowledge Share API is a Spring Boot application that provides a RESTful API for the DevOps Knowledge Share application. \
The API is used to manage the data for the DevOps Knowledge Share application. It is used to create, read, and delete data for the application. \
Application data is stored in a database. The application works in conjunction with the DevOps Knowledge Share UI, which is a React application containing a simple form and table.

The purpose of this application is to act as an example of a simple Java Spring Boot application allowing us to demonstrate how one might build, test, and deploy a Java Spring Boot application.

## Prerequisites

This project requires the following tools to be installed on your machine:

- [Make](https://www.gnu.org/software/make/#download)
- [Docker](https://docs.docker.com/get-docker/)
  - [Docker Compose](https://docs.docker.com/compose/install/#scenario-one-install-docker-desktop) _(v2.11.2+)_
- [pre-commit](https://pre-commit.com/#install)

## Running and developing locally

We use `make` to run commands during local development and leverage Docker and Docker Compose for execution so users do not need to install Java, Gradle, or other dependencies - everything runs in a consistent, containerized manner.

After cloning this repository you can run `make` or  `make help`  to see a list of available commands.

```bash
git clone <repository-url>
cd dks-api
make
```

### Building, testing, and runnning the application

There are a number of `make` targets available for this project but the most common ones are listed below. \
You can see a full list of available targets by running `make` or `make help`.

#### Build and test the application
```bash
make build
```

#### Run the application

```bash
make up
```

#### Run smoke test (requires the application to be running)

```bash
make smoke-test
```

#### Run functional tests (requires the application to be running)

```bash
make functional-test
```

#### Run Contract Tests

> :warning: **_NOTE:_** Contract Testing content is outdated and will be reviewed and updated in the near future.

To run the contract tests, you can either run Pact Broker locally using the resources in [this repository](<add link>), or you can connect to the production Pact Broker instance with the following environment variables set

> **_NOTE:_** If you decide to run Pact Broker locally, this will also require that the contract has been published from the consumer side. See the [DKS UI](https://github.com/liatrio/dks-ui) to see how to run the consumer contract tests and publish.

```
export PACT_BROKER_URL=<url>
export PACT_BROKER_SCHEME=https
export PACT_BROKER_PORT=443
export PACT_BROKER_USERNAME=<username>
export PACT_BROKER_PASSWORD=<password>
```

Run the contract tests using this Gradle task

```bash
./gradlew contractTest
```

## API Definitions

### Swagger UI Endpoint

   - `<DKS API URL>/swagger-ui.html`(e.g. http://localhost:8080/swagger-ui.html)

With the application running, navigate to the Swagger UI page to view and test existing DKS API endpoints.

### OpenAPI v3 Endpoint

   - `<DKS API URL>/v3/api-docs` (e.g. http://localhost:8080/v3/api-docs)

With the application running, navigate to the OpenAPI v3 definition page to see the Open API V3 definition for DKS API.

## Spring Boot Actuator

This application has Spring Boot Actuator enabled. This provides a number of endpoints for monitoring and managing the application.
Endpoints are enabled/disabled in  [`src/main/resources/application.properties` (link to file)](./src/main/resources/application.properties). \
Available endpoints can be found in the [Spring Boot Actuator documentation (v2.7.18)](https://docs.spring.io/spring-boot/docs/2.7.18/reference/html/actuator.html#actuator.endpoints).

### Health Check Endpoint

   - `<DKS API URL>/actuator/health` (e.g. http://localhost:8080/actuator/health)

With the application running, navigate to the health check endpoint to see the current health of the application.
