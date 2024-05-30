package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserRequest {
    private String id;
    private String firstname;
    private String lastname;
    private String username;

}