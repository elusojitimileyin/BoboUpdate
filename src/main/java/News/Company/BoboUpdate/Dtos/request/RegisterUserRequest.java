package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
