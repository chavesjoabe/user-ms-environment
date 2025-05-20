# Microservice Study Environment

This project demonstrates a microservice architecture using **Java (Quarkus)**, **Kafka**, **PostgreSQL**, and **S3 (via LocalStack)**. It simulates a user data flow through different services that communicate asynchronously using Kafka topics.

## 📌 Architecture Overview

```plaintext
+----------------+       +----------------+       +----------------------+
| test-service   |       | user-details   |       | user-s3-integration  |
| (CLI Producer) |       | (Kafka Consumer|       | (Kafka Consumer      |
|                |       |  + Producer)   |       |  + S3 Exporter)      |
+--------+-------+       +--------+-------+       +-----------+----------+
         |                        |                           |
         v                        v                           v
    [user-ids] Kafka Topic --> [user-details service] --> [user-data topic]
                                                             |
                                                             v
                                                      Save CSV to S3

```

## 🛠️ Technologies Used

- Java 17 + Quarkus
- Apache Kafka
- PostgreSQL
- Avro serialization
- AWS S3 (via LocalStack)

## 📁 Project Structure

```plaintext
.
├── db-init/                # SQL scripts or DB init config
├── docker-compose.yaml     # Main environment configuration
├── localstack/             # LocalStack setup
├── test-service/           # CLI producer application
├── user-details/           # Kafka consumer/producer service
└── user-s3-integration/    # Kafka consumer & S3 CSV exporter
```

## 🚀 Getting Started

2. Start the Environment

```bash
docker-compose up --build -d
```

Wait until all containers are healthy (LocalStack, PostgreSQL, Kafka, Zookeeper).

3. Create S3 Bucket on LocalStack
Before producing data, create the S3 bucket:

```bash
aws --endpoint-url http://localhost:4566 s3 mb s3://user-data-files
```

4. Run the Services
Each service can be run in development mode using Quarkus CLI. Open a new terminal for each:

### user-details

```bash
cd user-details
mvn quarkus:dev
```

### user-s3-integration

```bash
cd user-s3-integration
mvn quarkus:dev
```

5. Trigger the Process with test-service

```bash
cd test-service
mvn quarkus:dev -Dquarkus.args="user-ids-producer --count=1"
```

This will publish count number of messages to the Kafka topic user-ids.

## ✅ Expected Flow

test-service produces message(s) to user-ids topic.

user-details consumes those IDs, fetches data from the PostgreSQL database, and produces enriched messages to user-data.

user-s3-integration consumes messages from user-data, transforms them to CSV, and saves them to the S3 bucket user-data-files.

## 📂 Result

After running the flow, check the contents of the S3 bucket:

```bash
aws --endpoint-url http://localhost:4566 s3 ls s3://user-data-files/
```

You should see .csv files containing user data.

## 📸 Architecture Diagram

## 🧹 Cleanup

To stop and remove all running containers:

```bash
docker-compose down
```

To delete the S3 bucket:

```bash
aws --endpoint-url http://localhost:4566 s3 rb s3://user-data-files --force
```
