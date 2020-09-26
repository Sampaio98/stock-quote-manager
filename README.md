docker container run -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8
docker container run -p 8080:8080 -d lucasvilela/stock-manager
./mvnw clean package -DskipTests
docker build -t stock-quote-manager .
docker run -p 8081:8081 stock-quote-manager