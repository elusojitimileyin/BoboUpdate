package News.Company.BoboUpdate.Controller;

import News.Company.BoboUpdate.BoboUpdateException.BoboUpdateException;
import News.Company.BoboUpdate.Dtos.request.LoginUserRequest;
import News.Company.BoboUpdate.Dtos.request.LogoutUserRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.request.UpdateUserRequest;
import News.Company.BoboUpdate.Dtos.response.ApiResponse;
import News.Company.BoboUpdate.Dtos.response.UpdateUserResponse;
import News.Company.BoboUpdate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/BoboUpdate")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
           var result = userService.registerUser(registerUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,result),CREATED);
        } catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    @PatchMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            var result = userService.login(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutUserRequest logoutUserRequest) {
        try {
            var result = userService.logout(logoutUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    @PatchMapping("/update")
    public ResponseEntity<?> updateUserBio(@RequestBody UpdateUserRequest updateUserRequest) {

        try {
            UpdateUserResponse result = userService.updateUserBio(updateUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
}
