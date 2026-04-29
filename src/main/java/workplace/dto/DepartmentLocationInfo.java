package workplace.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentLocationInfo {
   
    private String departmentName;
    private String managerFullName;
    private String streetAddress;
    private String city;
    private String country;
    private String regionName;


    
}
