package workplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployee {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Double salary;
    private Long departmentId;


   
}
