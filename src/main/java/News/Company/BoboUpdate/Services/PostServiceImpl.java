package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Data.Repositories.PostRepository;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.request.DeletePostRequest;
import News.Company.BoboUpdate.Dtos.request.EditPostRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import News.Company.BoboUpdate.Dtos.response.DeletePostResponse;
import News.Company.BoboUpdate.Dtos.response.EditPostResponse;
import News.Company.BoboUpdate.Utils.BoboUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static News.Company.BoboUpdate.Utils.Mapper.*;
import static News.Company.BoboUpdate.Utils.Validators.validateEditedPost;
import static News.Company.BoboUpdate.Utils.Validators.validatedDeletePost;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findUserBy(createPostRequest.getUsername());
        Post newPost = createPostMapper(createPostRequest, foundUser);
        Post savedPost = postRepository.save(newPost);
        foundUser.getPosts().add(savedPost);
        userRepository.save(foundUser);
        return getCreatePostResponse(savedPost);
    }


    @Override
    public EditPostResponse editPost(EditPostRequest editPostRequest) {
        User user = findUserBy(editPostRequest.getUsername());
        Post post = validateEditedPost(editPostRequest, user);
        post.setDateTimeUpdated(LocalDateTime.now());
        postRepository.save(post);
        return getEditPostResponse(post);
    }

    @Override
    public DeletePostResponse deletePost(DeletePostRequest deletePostRequest) {
        User user = findUserBy(deletePostRequest.getUsername());
        Post post = validatedDeletePost(deletePostRequest, user);
        user.getPosts().remove(post);
        userRepository.save(user);
        postRepository.delete(post);
        return new DeletePostResponse();
    }



    @Override
    public User findUserBy(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new BoboUpdateException("User with username " + username + " not found");
        return user;
    }



    @Override
    public List<Post> getAllUsersPost() {
        return postRepository.findAll();
    }
}
