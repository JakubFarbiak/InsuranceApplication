# Insurance Application

## Description
This is a Spring Boot application for managing customer insurance policies.  

## Steps to Run
1. Create a database (e.g., MySQL) and update `application.properties` with credentials.
2. Run the Spring Boot application.
3. Navigate to `http://localhost:8080` to use the app.

## Known Issues
- Thymeleaf select option requires `value` attributes to work correctly.
- Insurance type must be selected; empty option is disabled.

## Steps to Reproduce Issues
1. Go to “Add Insurance” form.
2. Submit without selecting insurance type → error occurs.
3. Fixed by adding `value` attribute to all `<option>` tags.
