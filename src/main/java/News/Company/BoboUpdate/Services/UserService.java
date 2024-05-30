package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Dtos.request.LoginUserRequest;
import News.Company.BoboUpdate.Dtos.request.LogoutUserRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.request.UpdateUserRequest;
import News.Company.BoboUpdate.Dtos.response.LoginUserResponse;
import News.Company.BoboUpdate.Dtos.response.LogoutUserResponse;
import News.Company.BoboUpdate.Dtos.response.RegisterUserResponse;
import News.Company.BoboUpdate.Dtos.response.UpdateUserResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest signUpRequest);

    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);

    LogoutUserResponse logoutUser(LogoutUserRequest logoutUserRequest);

    User findUserBy(String username);

    List<User> getAllUser();

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
}
