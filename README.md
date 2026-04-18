lazy loading and eager loading are both strategies for managing how data is fetched from db
lazy loading delays loading related data untill its actually accessess, eager loading retrieves all necessary data in a single query

hibernate n+1 problem is performance issue
\
application makes n+1 calls when n is number of entitities
n is number of child objects fetched
example - employee and department have many to one relationship one department has many employees as parent-child
by default is lazy

hibernate executes 1 query + N additional queries instead of just 1
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id")
private Department department;

if i have 5 employees in db
i cann findall hibernate runs one query SELECT \* FROM employees;
and departments are not loaded yet.

then lets say i access departments
for (Employee e : employees) {
System.out.println(e.getDepartment().getName());
}

then for each employee query is ran according to their id
em1: SELECT \* FROM departments WHERE id = 1;
em2: SELECT \* FROM departments WHERE id = 3;
em3: SELECT \* FROM departments WHERE id = 1; and so on

this is case not for only loops but here
return Page<Employee>;
even if im just returning employees, hibernate fetches departments and for each employee runs separate query.

Here Reason is JSON serialization
when returining employee jackson does something like:
for (Employee e : employees) {
e.getFirstName();
e.getLastName();
e.getDepartment(); // 👈 IMPORTANT
}
e.getDepartment is trigger here and because of lazy loading
@ManyToOne(fetch = FetchType.LAZY)
hibernate says: “Oh, you are accessing department now? I didn’t load it yet… let me query DB.”

Controller returns Employee
↓
Jackson serializes it
↓
Jackson calls getDepartment()
↓
Hibernate loads department (SQL query)
↓
Repeat for every employee

How to stop

1. dont expose department @JsonIgnore
2. fetch it upfront JOIN FETCH
3. use DTO best practice

Problem
Jackson → calls getDepartment() → Hibernate fires query → repeats N times

dtos are data transfer objects and they dont return full entity
so we do return Page<EmployeeDto> instead of <Employee>

why this solves n+1 problem

DTOs dont have lazy-loaded relationships they are just plain data.

how data gets into DTO
instead of findAll
i write query
\\ @Query("""
SELECT new workplace.dto.EmployeeDTO(
e.firstName,
d.name
)
FROM Employee e
JOIN e.department d
""") \\
Page<EmployeeDTO> findAllDTO(Pageable pageable);
hibernate runs only one query
SELECT e.first_name, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id;

PROJECTION = selecting only part of your entity instead of whole object.

Add the following enhancements to the Spring project based on the sample HR database:

Create new entities: Location, Country, and Region.

Update the Department entity:

Add a relationship to Location.

Add a manager property (linked to the appropriate employee entity).

Implement an API endpoint to retrieve a list of all departments with the following fields:

Department name
Manager full name (concatenation of firstName and lastName)
Country
City
Street address

The API should support filtering by:
Country
City
