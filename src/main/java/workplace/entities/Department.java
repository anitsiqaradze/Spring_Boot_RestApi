package workplace.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")

public class Department {

    @Id
    @Column(name = "department_id")
    private Long id;
    @Column(name = "department_name")
    private String name;
    
}
