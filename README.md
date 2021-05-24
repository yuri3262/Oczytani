# Oczytani

## Run on localhost

1. Clone repository 
```
git clone https://github.com/yuri3262/Oczytani.git
cd Oczytani
```
2. Set parameters for local deployment - ask owners for DB_PASSWORD
```
sed -i "s|<DB_PASSWORD>|DB_PASSWORD|g" src/main/resources/application.properties
sed -i "s|oczytani-db|oczytani-test|g" src/main/resources/application.properties
sed -i "s|https://oczytani.azurewebsites.net|http://localhost:8080|g" src/test/java/com/project/bilbioteka/App/e2e/*
sed -i "s|localhost:9515|localhost:4444|g" src/test/java/com/project/bilbioteka/App/e2e/*
```
3. Run docker-compose - chrome webdriver and application
```
sudo docker-compose up
```
4. Go to localhost:8080 to test application
5. (Optional) Run tests
```
mvn test
```
or
```
sudo docker run -it --rm --network container:oczytani_chromewebdriver_1 --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn test
```
