package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String userId;
    private String firstname;
    private String lastname;
    private String username;

}