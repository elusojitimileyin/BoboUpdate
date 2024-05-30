package News.Company.BoboUpdate.Utils;

import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Data.Repositories.PostRepository;
import News.Company.BoboUpdate.Data.Repositories.UserRepository;
import News.Company.BoboUpdate.Dtos.request.DeletePostRequest;
import News.Company.BoboUpdate.Dtos.request.EditPostRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class Validators {

 private static UserRepository userRepository;

 private static PostRepository postRepository;

@Autowired
 public Validators (UserRepository userRepository, PostRepository postRepository){
    Validators.userRepository = userRepository;
    Validators.postRepository = postRepository;
}


    public static boolean isNullOrEmptyString(String str) {
        return str == null || str.trim().isEmpty() || str.isBlank();
    }

    public static void validateUserInput(RegisterUserRequest registerUserRequest) {
        if (isNullOrEmptyString(registerUserRequest.getUsername())) {
            throw new BoboUpdateException("Username cannot be null,empty or blank");
        }
        if (isNullOrEmptyString(registerUserRequest.getPassword())) {
            throw new BoboUpdateException("Password cannot be null,empty or blank");
        }
        if (isNullOrEmptyString(registerUserRequest.getFirstName())) {
            throw new BoboUpdateException("First name cannot be null,empty or blank");
        }
        if (isNullOrEmptyString(registerUserRequest.getLastName())) {
            throw new BoboUpdateException("Last name cannot be null,empty or blank");
        }
    }

    public static Post validateEditedPost(EditPostRequest editPostRequest, User user) {
        Optional<Post> optionalPost = postRepository.findById(editPostRequest.getPostId());
        if (optionalPost.isEmpty()) {
            throw new BoboUpdateException("Post not found");}
        Post post = optionalPost.get();
        if (!user.getId().equals(post.getPostId())) {
            throw new BoboUpdateException("Post does not belong to user");}
        if (editPostRequest.getTitle() != null && !editPostRequest.getTitle().isEmpty()) {
            post.setTitle(editPostRequest.getTitle());}
        if (editPostRequest.getContent() != null && !editPostRequest.getContent().isEmpty()) {
            post.setContent(editPostRequest.getContent());}
        return post;
    }
    public static Post validatedDeletePost(DeletePostRequest deletePostRequest, User user) {
        Optional<Post> optionalPost = postRepository.findById(deletePostRequest.getPostId());
        if (optionalPost.isEmpty()) {
            throw new BoboUpdateException("Post not found");}
        Post post = optionalPost.get();
        if (!post.getPostId().equals(user.getId())) {
            throw new BoboUpdateException("You are not authorized to delete this post");}
        return post;
    }
}
