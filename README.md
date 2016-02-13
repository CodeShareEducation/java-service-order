# Service Order
Project to work with service orders from a cel phone store made with: Java, JSF, Hibernate, EJB, Jboss AS 7 and PostgreSQL

## How to install

1. What do you need?

  1. Jboss AS 7
  2. PostgreSQL

2. Copy this text in the `standalone.xml` into the tag <subsystem xmlns="urn:jboss:domain:datasources:1.0">:

  The <user-name> and <password> values are for instance, it depends of the username and password that you chose to your postgres.

  ```xml
  <datasources>
      <datasource jndi-name="java:/service-order" pool-name="service-order" enabled="true" use-java-context="true">
          <connection-url>jdbc:postgresql://localhost:5432/serviceOrder</connection-url>
          <driver>org.postgresql</driver>
          <pool>
              <min-pool-size>10</min-pool-size>
              <max-pool-size>100</max-pool-size>
              <prefill>true</prefill>
          </pool>
          <security>
              <user-name>postgres</user-name>
              <password>postgres</password>
          </security>
      </datasource>
      <drivers>
          <driver name="org.postgresql" module="org.postgresql">
              <xa-datasource-class>org.postgresql.Driver</xa-datasource-class>
          </driver>
      </drivers>
  </datasources>
  ```

3. There is a folder into config `postgresql`, copy its content into `{jboss-home}/modules/org/` .
  For instance:

  ```
  cp -R postgresql {jboss-home}/modules/org/
  ```

4. Import the project to your IDE (Eclipse, Netbeans).

5. Config JBoss Server in the IDE.

6. Add the project to the server.

7. Start the server.

8. Open the browser and acess the adress http://localhost:8080/service-order
