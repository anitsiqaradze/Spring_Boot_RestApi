package workplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestData<T> {

    private T data;
    private Paging paging;

}
