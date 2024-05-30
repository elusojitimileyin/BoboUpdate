package News.Company.BoboUpdate.Services;
import News.Company.BoboUpdate.Utils.BoboUpdateException;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.LoginUserRequest;
import News.Company.BoboUpdate.Dtos.request.LogoutUserRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.request.UpdateUserRequest;
import News.Company.BoboUpdate.Dtos.response.LoginUserResponse;
import News.Company.BoboUpdate.Dtos.response.LogoutUserResponse;
import News.Company.BoboUpdate.Dtos.response.RegisterUserResponse;
import News.Company.BoboUpdate.Dtos.response.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static News.Company.BoboUpdate.Utils.Encryptor.checkPassword;
import static News.Company.BoboUpdate.Utils.Mapper.*;
import static News.Company.BoboUpdate.Utils.Validators.isNullOrEmptyString;
import static News.Company.BoboUpdate.Utils.Validators.validateUserInput;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepositories;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateUserInput(registerUserRequest);
        String username = registerUserRequest.getUsername().toLowerCase();
        validateUserExistByUsername(username.toLowerCase());
        registerUserRequest.setUsername(username);
        User myUser = map(registerUserRequest);
        userRepositories.save(myUser);
        return map(myUser);
    }

    public LoginUserResponse loginUser(LoginUserRequest loginRequest) {
        User user = userRepositories.findByUsername(loginRequest.getUsername());
        if (user == null || !checkPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new BoboUpdateException("Invalid username or password");}
        user.setLoggedIn(true);
        userRepositories.save(user);
        return new LoginUserResponse(user.getId(), user.getUsername().toLowerCase());
    }

    @Override
    public LogoutUserResponse logoutUser(LogoutUserRequest logoutUserRequest) {
        String username = logoutUserRequest.getUsername().toLowerCase();
        User user = findUserBy(username);
        if (user == null) throw new BoboUpdateException("User with username " + username + " not found");
        user.setLoggedIn(false);
        userRepositories.save(user);
        return new LogoutUserResponse(user.getUsername());
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = findUserBy(updateUserRequest.getUsername());
        updateMapper(updateUserRequest, user);
        userRepositories.save(user);
        return mapUpdateUserResponse(user);
    }
    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }
    @Override
    public User findUserBy(String username) {
        User user = userRepositories.findByUsername(username);
        if (user == null) throw new BoboUpdateException("User with username " + username + " not found");
        return user;
    }


    private void validateUserExistByUsername(String username) {
        boolean userExists = userRepositories.existsByUsername(username);
        if (userExists) throw new BoboUpdateException(String.format("%s already exists", username));
    }


}