package News.Company.BoboUpdate.Dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    boolean isSuccessful;
    Object data;
}

