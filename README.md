# HR Management API

### Spring Boot REST API backed by an HR sample database with departments, employees, locations, and regions.

## Features

### Employee Service Endpoints

    - GET /employees/all — returns employee details

    - GET /employees/{id} — returns employee based on path variable - id.

    - POST /employees/save — add entry of employee, passed as DTO copied to entity object by helper method

    - POST /employees/search\*\* — looks for record based on dynamically passed property of searchEmployee object, returns paginated data based on paging object properties

    - POST /employees/searchContactInf — searches record based on email or phone uses dynamic projection to return Generic object

    - PUT /employees/{id}\*\* — edit employee record

### Employee Service Endpoints

    - GET /departments/all — returns departments details

    - GET /departments/summary — returns department summary details - information related with other tables (manager, location)

    - GET /departments/{id} — fetch all details of department record based on its id

    - GET {id}/employees/search — fetch all employees of id specified department

### DATABASE SCHEMA

![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](./Untitled%20Diagram.png)

### Tech Stack

    Spring Boot, Spring Data JPA, H2, Lombok
