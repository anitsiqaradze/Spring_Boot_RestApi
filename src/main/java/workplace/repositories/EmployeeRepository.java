package workplace.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import workplace.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
