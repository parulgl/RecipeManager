# recipemanager

How to start the recipemanager application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/recipe-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080/recipe`
4. For more implementation details, refer `resources.ImplementationDetails.txt`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
