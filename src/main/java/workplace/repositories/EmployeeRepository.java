package workplace.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import workplace.dto.EmployeeInfo;
import workplace.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value="SELECT e.first_name, e.last_name, concat(m.first_name, ' ', m.last_name) , d.department_name FROM employees e " + 
            "LEFT JOIN departments d ON e.department_id = d.department_id " + 
            "LEFT JOIN employees m ON e.manager_id = m.employee_id",
          nativeQuery = true)
List<EmployeeInfo> findAllWithDetails();
    
    @Query("select e from Employee e where e.department.id = :depId")
    List<Employee> findAllByDepartment(@Param("depId") Long departmentId);


      @Query(
            value = "select * from employees e where e.department_id = :depId",
            nativeQuery = true
    )
     List<Employee> findAllByDepartmentViaNative(@Param("depId") Long departmentId);

      @Query(
        "select e from Employee e "+
        "where (:searchText is null or concat(e.firstName, concat(' ', e.lastName)) like :searchText)")
        Page<Employee> searchEmployees(@Param("searchText") String searchText, Pageable pageable);


       @Query(
        value = "select * from employees e "+
        "where (:searchText is null or concat(e.first_name, concat(' ', e.last_name)) like :searchText)",
        nativeQuery = true
       ) 
       Page<Employee> searchEmployeesViaNative(@Param("searchText") String searchText, Pageable pageable);
       

       // spring builts query from method name 
       /*
        SELECT ... FROM employees 
        WHERE phone = ? OR email = ?

        */
       <T> T findByPhoneOrEmail(String phone, String email, Class<T> type);


}
