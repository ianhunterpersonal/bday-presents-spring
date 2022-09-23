# Getting Started

##Overview

Project to allow users to set up gift lists and publish. This is a SpringBoot application 
with an 'associated' React frontend.

##Running and Testing

In order to run the server, it can be simply executed in STT and has a single entry 
point ___bday-presents-spring___. By default it runs on port 8081. On starting the 
service, class TestDataInserter will populate DB with some basic test data.

From STS you can run all unit tests which should pass.

In addition loading folder '/src/main/ui' into Visual Studio allows development of 
the react UI which can be executed in VSC using yarn start. 

##Documentation

The URL http://localhost:8081/swagger-ui.html starts Swagger and gives the basic REST 
endpoints that are documented and can be executed


##Notes

###GUI

   1. We have a crude route to 2 pages, one for home - / or /home and
     another for signin - /signin.
   2. Sign in in supposed to populate 'UserContext' with a TOKEN which <span style="color:red">**currently does 
      not work**</span>
   3. Pressing Log In on SignInPage does call server to get TOKEN

###SERVER

(Please refer to Swagger)

   1. On Start class TestDataInserter populates Gift and Person database with some 
      data.
   1. PersonResource API allows for simple CRUD
   1. GiftResource API allows simple CRUD
   1. JUnit tests and integration tests are written around GIFT and PERSON, <span style="color:red">**but not 
      SESSION**</span>
   1. A simple Cucumber test has been implemented logout happy/sad simple.
   1. The Gift unit tests are undeveloped

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

