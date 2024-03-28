package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Dtos.request.SignInRequest;
import News.Company.BoboUpdate.Dtos.request.SignOutRequest;
import News.Company.BoboUpdate.Dtos.request.SignUpRequest;

public interface UserService {
    void registerUser(SignUpRequest signUpRequest);

    void login(SignInRequest signInRequest);

    void logout (SignOutRequest signOutRequest);
}
