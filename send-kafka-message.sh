#!/bin/bash

FILENAME=$1

# Adjust to your Kafka installation folder
KAFKA_BIN=$HOME/bin/kafka_2.13-2.6.0/bin

# Attempt to create topic
$KAFKA_BIN/kafka-topics.sh --create --topic products --bootstrap-server localhost:9092

cat $FILENAME | $KAFKA_BIN/kafka-console-producer.sh --topic products --bootstrap-server localhost:9092