package workplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {

    private String firstName;
    private String lastName;
    private String managerName;
    private String departmentName;

    
}
