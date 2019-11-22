# Implementing the Outbox Pattern

## Blogs: 
- [Dzone](https://dzone.com/articles/implementing-the-outbox-pattern)
- [Medium](https://medium.com/@sohan_ganapathy/resilient-eventing-in-microservices-using-the-outbox-pattern-ed0b10ea3ef8)

## Prerequisites
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/v17.09/engine/installation/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Building and Installing required tools.

#### 1: Build the  `custom-debezium-connect` component.

```shell
cd custom-debezium-connect
mvn clean install
docker build -t custom-debezium-connect .
```

#### 2: Run the Docker-Compose under the project folder to install all the needed tools.

```shell
docker-compose up -d
```

## Setting up Kafka topics and Kafka Connectors.

#### - Setting up needed Kafka Topics
```shell
docker exec -t kafka /usr/bin/kafka-topics \
      --create --bootstrap-server :9092 \
      --topic student_email_changed \
      --partitions 1 \
      --replication-factor 1
      
docker exec -t kafka /usr/bin/kafka-topics \
      --create --bootstrap-server :9092 \
      --topic student_enrolled \
      --partitions 1 \
      --replication-factor 1
```

#### - Linking the Debezium Kafka Connect With the Outbox Table
```shell
curl -X POST \
  http://localhost:8083/connectors/ \
  -H 'content-type: application/json' \
  -d '{
   "name": "student-outbox-connector",
   "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": "postgres",
      "database.port": "5432",
      "database.user": "user",
      "database.password": "password",
      "database.dbname": "studentdb",
      "database.server.name": "pg-outbox-server",
      "tombstones.on.delete": "false",
      "table.whitelist": "public.outbox",
      "transforms": "outbox",
      "transforms.outbox.type": "com.sohan.transform.CustomTransformation"
   }
}'
```

#### - Consuming Kafka Topics
```shell
docker exec -t kafka /usr/bin/kafka-console-consumer \
      --bootstrap-server :9092 \
      --from-beginning \
      --topic student_enrolled

docker exec -t kafka /usr/bin/kafka-console-consumer \
      --bootstrap-server :9092 \
      --from-beginning \
      --topic student_email_changed
```

## Starting the `student-microservice`
```shell
mvn spring-boot:run
```

## Service endpoints.

#### Create Student
```shell
curl -X POST \
  'http://localhost:8080/students/~/enroll' \
  -H 'content-type: application/json' \
  -d '{ 
    "name": "Megan Clark",
    "email": "mclark@gmail.com",
    "address": "Toronto, ON"
}'
```

#### Update Student
```shell
curl -X PUT \  
  'http://localhost:8080/students/1/update-email' \  
  -H 'content-type: application/json' \
  -d '{"email": "jsmith@gmail.com"}'
```
