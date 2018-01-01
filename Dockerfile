FROM java:8
EXPOSE 8080:8080
ADD /target/spring-hibernate-example-1.0-SNAPSHOT.jar springdocker.jar
ENTRYPOINT ["java","-jar","springdocker.jar"]