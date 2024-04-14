package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String username;
    private String dateTimeRegistered;
    private boolean loggedIn;


}
