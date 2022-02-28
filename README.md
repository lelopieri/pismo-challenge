# pismo-challenge
Technical challenge for Pismo.io


## Prerequisites to run
- Java JDK 11
- Maven
- git
- Docker
- Docker Compose

## How To run
- Clone project 
```
git clone https://github.com/lelopieri/pismo-challenge.git
```

### Dev Environment
#### Run Postgres Docker image to use during development
   `docker run --rm --name pismo-postgres --network pismo-network -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -d postgres:14-alpine`
#### Run application through IDE 
   - Main Class: br.com.pieri.pismotechnicalchallenge.PismoTechnicalChallengeApplication
   - VM Options: -Dspring.profiles.active=dev

#### Run using Docker 
 ```
mvn clean package
docker build . -t pismo-challenge:latest
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=demo -e SPRING_DATASOURCE_URL=jdbc:postgresql://pismo-postgres:5432/postgres -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_PASSWORD=mypassword --network pismo-network pismo-challenge:latest
 ```

### Demo Environment with docker-compose
```
mvn clean package
docker-compose up -d
```

#### To stop use
```
docker-compose down
```

## How to use
Once application is running, you can invoke endpoints like:
```
http://localhost:8080/accounts
http://localhost:8080/accounts/1
```
You also can use Postman to powerfully interact with the API importing the Postman Collection
from file [Pismo Challenge.postman_collection.json](https://github.com/lelopieri/pismo-challenge/blob/main/Pismo%20Challenge.postman_collection.json)

