# Task Description

Read the requirements below, develop a new OpenAPI 3.0 yaml contract file (contract first approach) and implement a Spring Boot microservice implementing the OpenAPI 3.0 yaml contract. Gradle must be used as a build automation tool.


Description:

Currency conversion calculation service.

Conversion shall be done in two directions:

Client has some specific amount of EUR and wants to buy GBP. Service returns GBP amount that can be bought.

Client wants to buy some specific amount of GBP and wants to know what amount of EUR he needs. Service returns EUR amount that is required to buy a specific amount of GBP.


Sample use cases:

Client wants to buy some GBP by selling 100.00 EUR.

Client wants to buy 100.00 GBP by selling some EUR.


The current baseline rate is 1.2375, i.e. 1.00 GBP = 1.23 EUR. The rate shall be stored in a database, e.g. in H2 in-memory database.

The required margin is 2%. The margin is applied to the calculated part, i.e. to EUR in the 1st case, to GBP in the 2nd case. The margin shall be stored in application parameters (application.yml)

Input and output amounts should have 2 decimal places.


Task: Service should return required amount of EUR for the 1st case and calculated amount of GBP in the 2nd case.

### Requirements

```
Git        : v2.18.0 or higher
Java       : JDK 11 or higher
Gradle     : v7.4 or higher

OS         : Linux, MacOS or Windows
```

### Getting Started

```
git clone https://serpentcross@bitbucket.org/serpentcross/products-api.git
cd ./betoola/
```

### Preparing database

No actions required. The project uses H2 in-memory database.


### Installing main application

To install the program just run command (recommended)

(for Linux or MacOS)
```
./gradlew build
```

(for Windows)
```
gradlew.bat build
```

Or you may also just run(without the wrapper):

```
gradle build
```

### Starting application

Run next command

```
./gradlew bootRun
```

### Running and testing

The REST-api runs on address

```
http://localhost:8080/api
```

Possible test call

```
http://localhost:8080/api/rates/convert?amount=100&currency=GBP
```

Should return a dto in JSON format:

```
{
    "currencyIn": "GBP",
    "amountIn": 100,
    "result": {
        "currencyOut": "EUR",
        "amountOut": 79.19,
        "price": 1.23,
        "exchangeFee": 1.61
    }
}
```

### Documentaion

A detailed documentation, which contains full API description  is located there:
```
http://localhost:8080/api/swagger-ui/index.html
```

A contract file is located by address:

```
http://localhost:8080/api/v3/api-docs
```

### Possible upgrades

- wrap API in Docker container;
- using API-versioning;
- implementing audit feature (via AuditableEntity);