package workplace.dto;

import lombok.Data;

@Data
public class AddEmployee {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double salary;
    private Long departmentId;
    
}
