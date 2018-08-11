# evfinder-backend
REST Api used by EvFinder android application. 

## Requirements
- JDK 8

## Tutorial
To raise server up we need only to create jar file and execute it. In project directory just use `./mvnw clean package` and on created jar file `java -jar <path-to-file>`

That should start server localy on default 8080 port. Sometimes port is already in using, then probably you should edit application.properties to override default port.
```
server.port=8081 
```

Properties file can be also used to set database connection, but for more information look at [Spring Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html).
