1. The following code was built and tested on Tomcat 9 running on a Mac.
2. The war file to install can be found here - <ZlipLocation>/rest_api/target/rest_api.war
3. Libaries used: jersey, jackson, joda and ehcache
4. You can use the following urls below to test the services, or modify them and send in any or zero parameters for testing.
5. For testing on my side I used Postman for both GET/PUT operations

topseller - GET
http://localhost:8080/rest_api/webapi/topsellers?count=10&startDate=2009-12-10 08:28:04&endDate=2010-02-08 17:46:39

getsalesorder - GET
http://localhost:8080/rest_api/webapi/getsalesorder?orderId=945161671

putsalesorder - PUT
http://localhost:8080/rest_api/webapi/putsalesorder?orderId=25&product=product key 1&count=5&createdAt=8400000&updatedAt=8400000

jersey-guice-bootstrap
======================

RESTful Web Services Application Sample Project with Jersey 1.18.1 and Google Guice 3.0

Dependencies included
---------------------
- Servlet 2.5
- Jersey 1.18.1
- Guice 3.0
- guice-persist 3.0
- gson 2.2.4
- joda-time 2.4
- JUnit 4.10

Requirements
------------
- Java 7
- Maven 3
- Tomcat 7

Building
--------
- Make the war file <code> mvn clean package </code>
- Deploy the war file in Tomcat 7 with Eclipse or manually

After Deploy on Application Server
----------------------------------
- The REST WS are accessible under /rest/*
- Front-End not yet implemented.

Creating a Local Maven Archetype
--------------------------------
- Generate an archetype: <code> mvn archetype:create-from-project </code>
- Go to <code>target/generated-sources/archetype</code> and run <code>mvn install </code>
- Create a fresh project from Archetype <code> mvn archetype:generate -DarchetypeCatalog=local </code> using <code>com.pampanet:jersey-guice-bootstrap-archetype </code>
- From Eclipse you have check the "include snapshot archetypes" checkbox, and select the archetype from the catalog after installing it.
