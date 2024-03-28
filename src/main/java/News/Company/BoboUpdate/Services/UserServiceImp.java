package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.SignInRequest;
import News.Company.BoboUpdate.Dtos.request.SignOutRequest;
import News.Company.BoboUpdate.Dtos.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepositories; 
    @Override
    public void registerUser(SignUpRequest signUpRequest) {
        if (validateRegistration(signUpRequest)){
            User user = new User();
            user.setFirstName(signUpRequest.getFirstName());
            user.setLastName(signUpRequest.getLastName());
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(signUpRequest.getPassword());
            userRepositories.save(user);
        }else {
            throw new IllegalArgumentException("Registration Failed");
        }
        
    }

    private boolean validateRegistration(SignUpRequest signUpRequest) {
    return validateFirstName(signUpRequest.getFirstName()) &&
        validateLastName(signUpRequest.getLastName())&&
        validateUsername(signUpRequest.getUsername())&&
        validatePassword(signUpRequest.getPassword());

    }

    private boolean validatePassword(String password) {
        return password != null && !password.isEmpty();
    }

    private boolean validateUsername(String username) {
        return username != null && !username.isEmpty();
    }

    private boolean validateLastName(String lastName) {
        return lastName != null && !lastName.isEmpty();
    }

    private boolean validateFirstName(String firstName) {
        return firstName != null && !firstName.isEmpty();
    }

    @Override
    public void login(SignInRequest signInRequest) {
        Optional<User> userOptional = userRepositories.findById(signInRequest.getUsername());
        userOptional.filter(user -> user.getPassword().equals(signInRequest.getPassword()));

    }

    @Override
    public void logout(SignOutRequest signOutRequest) {
        Optional<User> userOptional = userRepositories.findById(signOutRequest.getUsername());


    }
}
