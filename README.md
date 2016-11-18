# Service Order
Project to work with service orders from a cel phone store made with: Java, JSF, CDI, Hibernate, EJB, Jboss and PostgreSQL, and take care with I18N. The languages available are English and Portuguese.

## How to install

1. What do you need?

  1. Jboss Wildfly 8.0.1
  2. PostgreSQL 9.4
  3. JDK 1.7 +

2. First you have to config your environment, to do this you can follow this [instructions](https://github.com/CodeShareEducation/java-service-order/blob/master/how-to-config-data-source-wildfly.md).

3. You'll need set the correct username and password of you database to the default value of application (username=postgres, password=postgres), or change them in the service-order-ds.xml file (it's into `service-order/service-order-ear/src/main/application/META-INF`).

4. Import the project to your IDE (IntelliJ, Eclipse, Netbeans).

5. Config JBoss Server in the IDE.

6. Add the project to the server.

7. Start the server.

8. Open the browser and acess the adress http://localhost:8080/service-order-web/
