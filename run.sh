#!/bin/bash

sed -i "s|<DB_PASSWORD>|yMGqDMM7\!WiYNM8|g" src/main/resources/application.properties
sed -i "s|oczytani-db|oczytani-test|g" src/main/resources/application.properties
sed -i "s|https://oczytani.azurewebsites.net|http://localhost:8080|g" src/test/java/com/project/bilbioteka/App/e2e/*
sed -i "s|localhost:9515|localhost:4444|g" src/test/java/com/project/bilbioteka/App/e2e/*

sudo docker-compose up