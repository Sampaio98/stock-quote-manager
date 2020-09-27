<h1>Os comandos abaixo são utilizados para inicializar o projeto</h1>

<h2>É necessário realizar em sequencia</h2>

<p>docker container run -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8</p>
<p>docker container run -p 8080:8080 -d lucasvilela/stock-manager</p>
<p>./mvnw clean package -DskipTests</p>
<p>docker build -t stock-quote-manager .</p>
<p>docker run -p 8081:8081 stock-quote-manager</p>




