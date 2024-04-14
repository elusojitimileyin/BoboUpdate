package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.BoboUpdateException.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findUserBy(createPostRequest.getUsername());

        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setDateTimeCreated(LocalDateTime.now());
        newPost.setUserId(foundUser.getId());

        Post savedPost = postRepository.save(newPost);
        foundUser.getPosts().add(savedPost);
        userRepository.save(foundUser);

        CreatePostResponse response = new CreatePostResponse();
        response.setPostId(savedPost.getId());
        response.setTitle(savedPost.getTitle());
        response.setContent(savedPost.getContent());
        response.setDateCreated(savedPost.getDateTimeCreated());
        return response;
    }

    @Override
    public EditPostResponse edit(EditPostRequest editPostRequest) {
        User user = findUserBy(editPostRequest.getUsername());

        Optional<Post> optionalPost = postRepository.findById(editPostRequest.getPostId());
        if (optionalPost.isEmpty()) {
            throw new PostNotFoundException("Post not found");
        }

        Post post = optionalPost.get();

        if (!user.getId().equals(post.getUserId())) {
            throw new UserNotFoundException("Post does not belong to user");
        }

        if (editPostRequest.getTitle() != null && !editPostRequest.getTitle().isEmpty()) {
            post.setTitle(editPostRequest.getTitle());
        }

        if (editPostRequest.getContent() != null && !editPostRequest.getContent().isEmpty()) {
            post.setContent(editPostRequest.getContent());
        }

        post.setDateTimeUpdated(LocalDateTime.now());
        postRepository.save(post);

        EditPostResponse response = new EditPostResponse();
        response.setPostId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setDateCreated(post.getDateTimeCreated().toString());
        return response;
    }

    @Override
    public DeletePostResponse delete(DeletePostRequest deletePostRequest) {
        User user = findUserBy(deletePostRequest.getUsername());

        Optional<Post> optionalPost = postRepository.findById(deletePostRequest.getPostId());
        if (optionalPost.isEmpty()) {
            throw new PostNotFoundException("Post not found");
        }

        Post post = optionalPost.get();

        if (!post.getUserId().equals(user.getId())) {
            throw new BoboUpdateException("You are not authorized to delete this post");
        }

        user.getPosts().remove(post);
        userRepository.save(user);
        postRepository.delete(post);

        return new DeletePostResponse();
    }

    @Override
    public User findUserBy(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
        return user;
    }


    @Override
    public List<Post> getUserPost(String username) {
        User user = findUserBy(username);
        return postRepository.findByUserId(user.getId());
    }
}
