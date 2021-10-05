### Coding Standards
The coding standards being used are mentioned here https://google.github.io/styleguide/javaguide.html.
Please install [this](https://github.com/google/google-java-format) on your IDE to format your code
### Requirements
You must have the following installed to start developing/ run this project
   1. jdk1.8 (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
   2. maven (https://maven.apache.org/install.html)
   
### Steps to run
Once in the directory use the following commands 
```
mvn package
java -jar target/span-front-end-0.1-jar-with-dependencies.jar 
```
### To run test cases
```
mvn test
```

### To just compile
```
mvn compile
```

### Rules to be followed while writing grammar
1. Terminals are to be in TitleCase and non terminals are to be written in camelCase.
2. While using rule labels, ensure you're uniquely identifying them. Also prepend the initials of the grammar 
rule to separate them.