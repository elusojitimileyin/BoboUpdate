package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;

@Data
public class UpdateUserResponse {
    private String firstname;
    private String lastname;
    private String userId;
    private String username;
    private String dateUpdated;
    private boolean loggedIn;
}