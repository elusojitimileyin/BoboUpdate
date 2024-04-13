package News.Company.BoboUpdate.Services;
import News.Company.BoboUpdate.BoboUpdateException.BoboUpdateException;
import News.Company.BoboUpdate.BoboUpdateException.UserExistException;
import News.Company.BoboUpdate.BoboUpdateException.UserNotFoundException;
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

import static News.Company.BoboUpdate.Utils.Mapper.map;
import static News.Company.BoboUpdate.Utils.Mapper.mapUpdateUserResponse;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepositories;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        String username = registerUserRequest.getUsername().toLowerCase();
        registerUserRequest.setUsername(username);
        validate(username.toLowerCase());
        User myUser = map(registerUserRequest);
        userRepositories.save(myUser);

        return map(myUser);
    }

    public LoginUserResponse login(LoginUserRequest loginRequest) throws BoboUpdateException {
        User user = userRepositories.findByUsername(loginRequest.getUsername());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new BoboUpdateException("Invalid username or password");
        }
        LoginUserResponse response = new LoginUserResponse();
        response.setUsername(user.getUsername());

        return response;
    }

    @Override
    public LogoutUserResponse logout(LogoutUserRequest logoutUserRequest) {
        String username = logoutUserRequest.getUsername().toLowerCase();
        User user = findUserBy(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found");
        } else {
            return new LogoutUserResponse(user.getId(), user.getUsername());
        }
    }

    @Override
    public User findUserBy(String username) {
        User user = userRepositories.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
        return user;
    }


    private void validate(String username) {
        boolean userExists = userRepositories.existsByUsername(username);
        if (userExists) throw new UserExistException(String.format("%s already exists", username));
    }

    @Override
    public UpdateUserResponse updateUserBio(UpdateUserRequest updateUserRequest) {
        String userId = updateUserRequest.getUserId();
        User user = userRepositories.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        user.setFirstName(updateUserRequest.getFirstname());
        user.setLastName(updateUserRequest.getLastname());
        user.setUsername(updateUserRequest.getUsername());
        user.setDateRegistered(LocalDateTime.now());
        userRepositories.save(user);
        return mapUpdateUserResponse(user);
    }
}