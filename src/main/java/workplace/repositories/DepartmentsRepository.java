package workplace.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import workplace.entities.Department;

public interface DepartmentsRepository extends JpaRepository<Department, Long> {
    
}
