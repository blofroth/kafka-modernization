# Quarkus Kafka Product cache demo project

Demo project for the Meetup presentation, *Modernizing Legacy from J2EE-monoliths to Microservices and Event Streaming*. 

https://www.meetup.com/Cloud-Transformation/events/271980635/

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `kafka-modernization-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/kafka-modernization-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/kafka-modernization-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Starting Kafka 
```bash
cd path/to/kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
bin/kafka-topics.sh --create --topic products --bootstrap-server localhost:9092
# send a message
cat product1_v1.json | bin/kafka-console-producer.sh --topic products --bootstrap-server localhost:9092

# or use the script (creates the topic)
sh send-kafka-message.sh product1_v1.json
```

Alternatively start Kafka with `docker-compose`:

```bash
docker-compose up
sh send-kafka-message.sh product1_v1.json
```
