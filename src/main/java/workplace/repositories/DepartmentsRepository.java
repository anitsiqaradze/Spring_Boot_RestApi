package workplace.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import workplace.entities.Department;
import workplace.dto.DepartmentLocationInfo;

public interface DepartmentsRepository extends JpaRepository<Department, Long> {

    

     @Query(
        value="SELECT d.department_name,"+ "CONCAT(e.first_name,\' \',e.last_name) AS manager_full_name , l.street_address,l.city, c.country_name, r.region_name "+
                        "FROM departments d " + 
                        "INNER JOIN employees e ON d.manager_id = e.employee_id " + 
                        "INNER JOIN locations l ON d.location_id = l.location_id " + 
                        "INNER JOIN countries c ON l.country_id = c.country_id " + 
                        "INNER JOIN regions r ON r.region_id = c.region_id",

        nativeQuery = true
     )

      List<DepartmentLocationInfo> findDepartmentSummaries();

//     @Query(value="SELECT d.department_name,"+ "CONCAT(e.first_name,\' \',e.last_name) AS manager_full_name , l.street_address, c.country_name, r.region_name "+
//                "FROM departments d " +
//                "INNER JOIN employees e ON d.manager_id = e.employee_id " +
//                "INNER JOIN locations l ON d.location_id = l.location_id " +
//                "INNER JOIN countries c ON l.country_id = c.country_id " +
//                "INNER JOIN regions r ON r.region_id = c.region_id"+
//                "WHERE LOWER(l.city) = LOWER(:city)",
//                nativeQuery = true
//        )
//      List<DepartmentLocationInfo> findByCity(@Param("city") String city);

//    @Query(value="SELECT d.department_name,"+ "CONCAT(e.first_name,\' \',e.last_name) AS manager_full_name , l.street_address, c.country_name, r.region_name "+
//            "FROM departments d " +
//            "INNER JOIN employees e ON d.manager_id = e.employee_id " +
//            "INNER JOIN locations l ON d.location_id = l.location_id " +
//            "INNER JOIN countries c ON l.country_id = c.country_id " +
//            "INNER JOIN regions r ON r.region_id = c.region_id"+
//            "WHERE LOWER(l.city) = LOWER(:city)",
//            nativeQuery = true
//    )
//    List<DepartmentLocationInfo> findByCountry(@Param("country") String country);

    @Query("SELECT d FROM Department d JOIN d.location l WHERE LOWER(l.city) = LOWER(:city)")
    List<Department> findByCity(@Param("city") String city);

    @Query("SELECT d FROM Department d JOIN d.location l JOIN l.country c WHERE LOWER(c.name) = LOWER(:countryName)")
    List<Department> findByCountryName(@Param("countryName") String countryName);




}
