FROM openjdk:8
MAINTAINER Allisson Sampaio
VOLUME /tmp
EXPOSE 8081
ADD target/stock-quote-manager-0.0.1-SNAPSHOT.jar stock-quote-manager.jar
ENTRYPOINT ["java","-jar","/stock-quote-manager.jar"]