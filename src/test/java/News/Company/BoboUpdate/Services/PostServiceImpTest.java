package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Data.Repositories.PostRepository;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.*;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import News.Company.BoboUpdate.Dtos.response.LoginUserResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImpTest {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        postRepository.deleteAll();

        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("@Twi1234");
        userRegisterRequest.setFirstName("firstName");
        userRegisterRequest.setLastName("lastName");
        userService.registerUser(userRegisterRequest);
        assertThat(userRepository.count(), is(1L));

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        LoginUserResponse loginResponse = userService.loginUser(loginRequest);
        assertThat(loginResponse.getUsername(), is("username"));

    }


    @Test
    void testThatUserCanCreatePostWithEmptyContent() {
        assertTrue(userRepository.existsByUsername("username"));

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("Empty Content Post");
        createPostRequest.setContent("");
        CreatePostResponse createPostResponse = postService.createPost(createPostRequest);
        assertEquals("", createPostResponse.getContent());

        User user = userRepository.findByUsername("username");
        assertThat(user.getPosts().size(), is(1));
    }

    @Test
    void testThatUserCanCreatePost() {
        assertTrue(userRepository.existsByUsername("username"));

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("this tech money must be made");
        createPostRequest.setContent("i done suffer for this learning curve ooo!");

        CreatePostResponse createPostResponse = postService.createPost(createPostRequest);
        assertEquals("this tech money must be made", createPostResponse.getTitle());
        assertEquals("i done suffer for this learning curve ooo!", createPostResponse.getContent());

        User user = userRepository.findByUsername("username");
        assertThat(user.getPosts().size(), is(1));
    }

    @Test
    void testThatUserCanCreateMultiplePost() {
        assertTrue(userRepository.existsByUsername("username"));

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("this tech money must be made");
        createPostRequest.setContent("i done suffer for this learning curve ooo!");

        CreatePostResponse createPostResponse = postService.createPost(createPostRequest);
        assertEquals("this tech money must be made", createPostResponse.getTitle());
        assertEquals("i done suffer for this learning curve ooo!", createPostResponse.getContent());

        CreatePostRequest createPostRequest1 = new CreatePostRequest();
        createPostRequest1.setUsername("username");
        createPostRequest1.setTitle("this tech money must be made");
        createPostRequest1.setContent("i done suffer for this learning curve ooo!");

        CreatePostResponse createPostResponse1 = postService.createPost(createPostRequest);
        assertEquals("this tech money must be made", createPostResponse1.getTitle());
        assertEquals("i done suffer for this learning curve ooo!", createPostResponse.getContent());

        User user = userRepository.findByUsername("username");
        assertThat(user.getPosts().size(), is(2));
        assertEquals(2, user.getPosts().size());
    }

    @Test
    void testThatUserCanEditCreatedPost() {
        assertTrue(userRepository.existsByUsername("username"));

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("this tech money must be made");
        createPostRequest.setContent("i done suffer for this learning curve ooo!");

        CreatePostResponse createPostResponse = postService.createPost(createPostRequest);

        EditPostRequest editPostRequest = new EditPostRequest();
        editPostRequest.setUsername("username");
        editPostRequest.setPostId(createPostResponse.getPostId());
        editPostRequest.setTitle("Edited Title");
        editPostRequest.setContent("Edited Content");
        postService.editPost(editPostRequest);

        User foundUser = userRepository.findByUsername("username");
        Post updatedPost = foundUser.getPosts().getFirst();
        assertEquals(1, foundUser.getPosts().size());
        assertTrue(updatedPost.getTitle().contains("Edited Title"));
        assertTrue(updatedPost.getContent().contains("Edited Content"));
    }

    @Test
    void testThatUserCanDeleteCreatedPost() {
        assertTrue(userRepository.existsByUsername("username"));

        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("this tech money must be made");
        createPostRequest.setContent("i done suffer for this learning curve ooo!");

        CreatePostResponse createPostResponse = postService.createPost(createPostRequest);
        assertThat(userRepository.findByUsername("username").getPosts().size(), is(1));
        DeletePostRequest deletePostRequest = new DeletePostRequest();
        deletePostRequest.setUsername("username");
        deletePostRequest.setPostId(createPostResponse.getPostId());
        postService.deletePost(deletePostRequest);
        assertFalse(postRepository.existsById(createPostResponse.getPostId()));
        assertThat(userRepository.findByUsername("username").getPosts().size(), is(0));

    }}