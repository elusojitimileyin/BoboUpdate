package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.BoboUpdateException.BoboUpdateException;
import News.Company.BoboUpdate.Data.Repositories.PostRepository;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.LoginUserRequest;
import News.Company.BoboUpdate.Dtos.request.LogoutUserRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.response.LoginUserResponse;
import News.Company.BoboUpdate.Dtos.response.LogoutUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceImpTest {

    @Autowired
    UserService userServices;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void registerOneUser_userIsOne() {
        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.registerUser(userRegisterRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerExistingUser() {
        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.registerUser(userRegisterRequest);

        assertThrows(BoboUpdateException.class, () -> userServices.registerUser(userRegisterRequest));

        assertEquals(1, userRepository.count());
    }

    @Test
    void testThatUserCanLoginAfterRegistration() {
        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.registerUser(userRegisterRequest);
        assertThat(userRepository.count(), is(1L));

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        LoginUserResponse loginResponse = userServices.login(loginRequest);
        assertThat(loginResponse.getUsername(), is("username"));
    }
    @Test
    void testThatUserCanRegister_login_logout() {
        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userServices.registerUser(userRegisterRequest);
        assertThat(userRepository.count(), is(1L));

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        LoginUserResponse loginResponse = userServices.login(loginRequest);
        assertThat(loginResponse.getUsername(), is("username"));

        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("username");
        LogoutUserResponse logoutResponse = userServices.logout(logoutRequest);
        assertThat( logoutResponse.getUsername(), is("username"));
    }

    @Test
    void testThatICanNotLoginIfIDontRegister(){
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("fela");
        loginRequest.setPassword("onigbo");

//        try{
//            userServices.login(loginRequest);
//
//        } catch (BoboUpdateException message){
//            assertEquals("User with username fela not found", message.getMessage());
//
//        }
        assertThrows(BoboUpdateException.class, () -> userServices.login(loginRequest));
        assertThat(userRepository.count(), is(0L));
    }

    @Test
    void testThatICantLogoutIfIDontLogin() {
        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("fela");

        assertThrows(BoboUpdateException.class, () -> userServices.logout(logoutRequest));
        assertThat(userRepository.count(), is(0L));
    }



@Test
void testThatUserCannotLoginWithWrongPasswordAfterRegistration() {
    RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
    userRegisterRequest.setUsername("username");
    userRegisterRequest.setPassword("password");
    userRegisterRequest.setFirstName("firstName");
    userRegisterRequest.setLastName("lastName");
    userServices.registerUser(userRegisterRequest);
    assertThat(userRepository.count(), is(1L));

    LoginUserRequest loginRequest = new LoginUserRequest();
    loginRequest.setUsername("username");
    loginRequest.setPassword("wrongPassword");
    try {
        userServices.login(loginRequest);
    } catch (BoboUpdateException e) {
        assertEquals("Invalid username or password", e.getMessage());
    }

    assertThat(userRepository.count(), is(1L));
}

}
