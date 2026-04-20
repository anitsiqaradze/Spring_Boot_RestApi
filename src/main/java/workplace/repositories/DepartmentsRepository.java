package workplace.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import workplace.entities.Department;
import workplace.dto.DepartmentInfo;

public interface DepartmentsRepository extends JpaRepository<Department, Long> {

    

     @Query(
        value="SELECT d.department_name,"+ "CONCAT(e.first_name,\' \',e.last_name) AS manager_full_name , l.street_address, c.country_name, r.region_name "+
                        "FROM departments d " + 
                        "INNER JOIN employees e ON d.manager_id = e.employee_id " + 
                        "INNER JOIN locations l ON d.location_id = l.location_id " + 
                        "INNER JOIN countries c ON l.country_id = c.country_id " + 
                        "INNER JOIN regions r ON r.region_id = c.region_id",

        nativeQuery = true
     )

     public List<DepartmentInfo> findDepartmentSummaries();

    
    
    
}
