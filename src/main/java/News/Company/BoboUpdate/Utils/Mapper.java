package News.Company.BoboUpdate.Utils;

import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.response.RegisterUserResponse;
import News.Company.BoboUpdate.Dtos.response.UpdateUserResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterUserRequest registerUserRequest ) {

        User user = new User();
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        return user;
    }
    public static RegisterUserResponse map(User user){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setId(user.getId());
        registerUserResponse.setDateTimeRegistered(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss").format(user.getDateRegistered()));
        return registerUserResponse;
    }
    public static UpdateUserResponse mapUpdateUserResponse(User user) {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstname(user.getFirstName());
        response.setLastname(user.getLastName());
        response.setDateUpdated(user.getDateUpdated().toString());
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }


}