package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Utils.BoboUpdateException;
import News.Company.BoboUpdate.Data.Repositories.PostRepository;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.LoginUserRequest;
import News.Company.BoboUpdate.Dtos.request.LogoutUserRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.request.UpdateUserRequest;
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

        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("@Twi1234");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");

        userServices.registerUser(userRegisterRequest);
    }

    @Test
    public void registerOneUser_userIsOne() {

        assertEquals(1, userRepository.count());
    }
    @Test
    void testThatUserCanLoginAfterRegistration() {
        assertThat(userRepository.count(), is(1L));

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        LoginUserResponse loginResponse = userServices.loginUser(loginRequest);
        assertThat(loginResponse.getUsername(), is("username"));
    }
    @Test
    void testThatUserCanRegister_login_logout() {
        assertThat(userRepository.count(), is(1L));

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        LoginUserResponse loginResponse = userServices.loginUser(loginRequest);
        assertThat(loginResponse.getUsername(), is("username"));

        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("username");
        LogoutUserResponse logoutResponse = userServices.logoutUser(logoutRequest);
        assertThat( logoutResponse.getUsername(), is("username"));
    }

    @Test
    void testThatICanNotLoginIfIDontRegister(){
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("fela");
        loginRequest.setPassword("@Twi1234");
        assertThrows(BoboUpdateException.class, () -> userServices.loginUser(loginRequest));

    }

    @Test
    void testThatICantLogoutIfIDontLogin() {
        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("fela");
        assertThrows(BoboUpdateException.class, () -> userServices.logoutUser(logoutRequest));

    }

@Test
void testThatUserCannotLoginWithWrongPasswordAfterRegistration() {

    assertThat(userRepository.count(), is(1L));
    LoginUserRequest loginRequest = new LoginUserRequest();
    loginRequest.setUsername("username");
    loginRequest.setPassword("wrongPassword");
    try {
        userServices.loginUser(loginRequest);
    } catch (BoboUpdateException e) {
        assertEquals("Invalid username or password", e.getMessage());
    }
    assertThat(userRepository.count(), is(1L));
}

    @Test
    public void registerOneUser_UpdateUser() {
        assertEquals(1, userRepository.count());
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(updateUserRequest.getId());
        updateUserRequest.setUsername("username");
        updateUserRequest.setFirstname("firstName1");
        updateUserRequest.setLastname("lastName1");
        userServices.updateUser(updateUserRequest);
        assertEquals(1, userRepository.count());
    }
}
