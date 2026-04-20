package workplace.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class DepartmentInfo {
   
    private String departmentName;
    private String firstName;  
    private String lastName;
    private String country;
    private String city;
    private String streetAddress;
    
}
