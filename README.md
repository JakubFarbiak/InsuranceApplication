# Insurance Application

A Spring Boot application for managing customer insurance policies.  
It allows creating customers, adding insurances, and viewing customer details.

---

## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/JakubFarbiak/InsuranceApplication.git
cd InsuranceApplication
2. Create the database
Before running the application, create a database in your preferred DBMS (MySQL, PostgreSQL, etc.):

Database name: insurance_db (you can choose another name, but update application.properties accordingly)

Example for MySQL:

sql
Copy code
CREATE DATABASE insurance_db;
3. Update application.properties
Make sure the database URL, username, and password match your setup:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
4. Build and run the application
bash
Copy code
mvn clean install
mvn spring-boot:run
5. Access the application
Open your browser and go to:
http://localhost:8080

Known Issues
Thymeleaf select option error:
If the <select> field in the insurance form does not have a value attribute for each <option>, the application will throw an exception:
org.thymeleaf.exceptions.TemplateProcessingException: Attribute "value" is required in "option" tags


