package workplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchEmployee {

    private String searchText;
    private String email;
    private String phone;


}
