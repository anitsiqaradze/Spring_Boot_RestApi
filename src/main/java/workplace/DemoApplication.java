package workplace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}

}
// additional notes
// -- Native SQL
// SELECT * FROM employees WHERE department_id = 1

// -- JPQL
// @Query("select e from Employee e where e.department.id = :deptId")
// List<Employee> findByDepartment(@Param("deptId") Long deptId);

// -- Native SQL
// SELECT * FROM employees ORDER BY last_name ASC

// -- JPQL
// @Query("select e from Employee e order by e.lastName asc")
// List<Employee> findAllOrderedByLastName();
-- Native SQL
SELECT * FROM employees WHERE last_name LIKE '%smith%'

-- JPQL
@Query("select e from Employee e where lower(e.lastName) like lower(concat('%', :name, '%'))")
List<Employee> findByLastNameContaining(@Param("name") String name);

-- Native SQL
SELECT COUNT(*) FROM employees WHERE department_id = 1

-- JPQL
@Query("select count(e) from Employee e where e.department.id = :deptId")
Long countByDepartment(@Param("deptId") Long deptId);


-- Native SQL
SELECT d.name, COUNT(e.id) 
FROM departments d 
JOIN employees e ON e.department_id = d.id 
GROUP BY d.name

-- JPQL
@Query("select d.name as departmentName, count(e) as employeeCount " +
       "from Department d join d.employees e " +
       "group by d.name")
List<DepartmentCountView> countEmployeesPerDepartment();

// projection
public interface DepartmentCountView {
    String getDepartmentName();
    Long getEmployeeCount();
}

-- Native SQL
SELECT SUM(salary) FROM employees WHERE department_id = 1

-- JPQL
@Query("select sum(e.salary) from Employee e where e.department.id = :deptId")
BigDecimal sumSalaryByDepartment(@Param("deptId") Long deptId);


-- Native SQL
SELECT d.name, SUM(e.salary)
FROM departments d
JOIN employees e ON e.department_id = d.id
GROUP BY d.name

-- JPQL
@Query("select d.name as departmentName, sum(e.salary) as totalSalary " +
       "from Department d join d.employees e " +
       "group by d.name")
List<DepartmentSalaryView> sumSalaryPerDepartment();

-- Native SQL
SELECT AVG(salary) FROM employees

-- JPQL
@Query("select avg(e.salary) from Employee e")
Double getAverageSalary();

-- Native SQL
SELECT d.name, AVG(e.salary)
FROM departments d
JOIN employees e ON e.department_id = d.id
GROUP BY d.name

-- JPQL
@Query("select d.name as departmentName, avg(e.salary) as averageSalary " +
       "from Department d join d.employees e " +
       "group by d.name")
List<DepartmentAvgSalaryView> avgSalaryPerDepartment();

-- Native SQL
SELECT d.name, COUNT(e.id)
FROM departments d
LEFT JOIN employees e ON e.department_id = d.id
GROUP BY d.name

-- JPQL
@Query("select d.name as departmentName, count(e) as employeeCount " +
       "from Department d left join d.employees e " +
       "group by d.name")
List<DepartmentCountView> countEmployeesIncludingEmpty();


	-- Native SQL
SELECT e.first_name, e.last_name, d.name
FROM employees e
JOIN departments d ON d.id = e.department_id
WHERE d.name = :deptName

-- JPQL
@Query("select e.firstName as firstName, e.lastName as lastName, " +
       "d.name as departmentName " +
       "from Employee e join e.department d " +
       "where d.name = :deptName")
List<EmployeeDeptView> findByDepartmentName(@Param("deptName") String deptName);

-- Native SQL (Spring handles LIMIT/OFFSET automatically)
SELECT * FROM employees WHERE department_id = 1

-- JPQL
@Query("select e from Employee e where e.department.id = :deptId")
Page<Employee> findByDepartment(@Param("deptId") Long deptId, Pageable pageable);
