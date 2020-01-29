# Transaction provider translator
Lightweight web application for storing transactions from several providers. 

Source files are available on GitHub https://github.com/martyjuliani/transaction-provider.

## Prerequisites
The project can be imported into the IDE of your choice as a Maven project with Java 11 installed.

## Project Structure

The project is following the standard [Maven project layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).
To compile the entire project, run `mvn clean install` in the project root.

## Public REST API
 - GET http://localhost:8080/transactions with request body with transactions as a string
  
## Build the project
Run `mvn clean install` from terminal.
  
## Running the project from command line
Run Web Application: `mvn spring-boot:run` or `java -jar target\transaction-provider-1.0.0.jar` in the project root directory. After the server has started 
point your browser to [http://localhost:8080/transactions-ui/](http://localhost:8080/transactions-ui) to see the resulting 
application. 

Run Web Application with command line output: `mvn spring-boot:run -Dspring-boot.run.arguments=location-to-file` or 
`java -jar target\transaction-provider-1.0.0.jar location-to-file`. You can check output in the console immediately 
after application start, e.g.:

 2020-01-29 23:58:44.432  INFO 4264 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
 2020-01-29 23:58:44.435  INFO 4264 --- [  restartedMain] c.j.t.TransactionsApplication            : Started TransactionsApplication in 16.198 seconds (JVM running for 17.001)
 Netflix|02|payment weekly\
 Apple|1|game Of Thrones\
 Netflix|01|payment yearly\
 ...

## Running the project from your IDE
Navigate to the `com.juleq.transactions.TransactionProviderApplication.java` class and run it as a Java application.  

## Running Tests
All tests are implemented by using Groovy, Mockito, Spock and TestNG.

Run `mvn test` from command line or use TransactionProvider profile from the Configuration menu.

## Branching information:
* `master` the latest version of the application
