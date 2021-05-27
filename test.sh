#!/bin/bash

sudo docker run -it --rm --network container:oczytani_chromewebdriver_1 --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn test
