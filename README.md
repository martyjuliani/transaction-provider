# Transaction provider translator

Lightweight web application for storing transactions from several providers. 

Source files are available on GitHub https://github.com/martyjuliani/transaction-provider.

## Prerequisites

The project can be imported into the IDE of your choice as a Maven project with Java 11 installed.

## Project Structure

The project is following the standard [Maven project layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).
To compile the entire project, run `mvn clean install` in the project root.

## Public REST API
 - GET http://localhost:8080/transaction-provider/transactions 
  
## Running the project from command line

Run `mvn clean install spring-boot:run` in the project root directory. After the server has started point your browser to [http://localhost:8080/cipher-translator-ui](http://localhost:8080/transactions-ui) to see the resulting application.

## Running the project from your IDE

Navigate to the `com.juleq.transactions.TransactionProviderApplication.java` class and run it as a Java application.  

## Running Tests

All tests are implemented by using Groovy, Mockito, Spock and TestNG.

Run `mvn test` from command line or use TransactionProvider profile from the Configuration menu.

## Branching information:
* `master` the latest version of the application
