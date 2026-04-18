package workplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeContactInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
