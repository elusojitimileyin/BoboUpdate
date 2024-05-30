package News.Company.BoboUpdate.Utils;

import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.request.RegisterUserRequest;
import News.Company.BoboUpdate.Dtos.request.UpdateUserRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import News.Company.BoboUpdate.Dtos.response.EditPostResponse;
import News.Company.BoboUpdate.Dtos.response.RegisterUserResponse;
import News.Company.BoboUpdate.Dtos.response.UpdateUserResponse;
import java.time.LocalDateTime;

import static News.Company.BoboUpdate.Utils.Encryptor.encryptPassword;


public class Mapper {
    public static User map(RegisterUserRequest registerUserRequest ) {

        User user = new User();
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(encryptPassword(registerUserRequest.getPassword()));
        return user;
    }
    public static RegisterUserResponse map(User user){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setDateTimeRegistered(LocalDateTime.now());
        return registerUserResponse;
    }

    public static void updateMapper(UpdateUserRequest updateUserRequest, User user) {
        user.setFirstName(updateUserRequest.getFirstname());
        user.setLastName(updateUserRequest.getLastname());
        user.setUsername(updateUserRequest.getUsername());
        user.setDateRegistered(LocalDateTime.now());
    }
    public static UpdateUserResponse mapUpdateUserResponse(User user) {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstname(user.getFirstName());
        response.setLastname(user.getLastName());
        response.setDateUpdated(user.getDateUpdated().toString());
        return response;
    }



    public static Post createPostMapper(CreatePostRequest createPostRequest, User foundUser) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setDateTimeCreated(LocalDateTime.now());
        newPost.setPostId(foundUser.getId());
        return newPost;
    }

    public static CreatePostResponse getCreatePostResponse(Post savedPost) {
        CreatePostResponse response = new CreatePostResponse();
        response.setPostId(savedPost.getPostId());
        response.setTitle(savedPost.getTitle());
        response.setContent(savedPost.getContent());
        response.setDateTimeCreated(savedPost.getDateTimeCreated());
        return response;
    }

    public static EditPostResponse getEditPostResponse(Post post) {
        EditPostResponse response = new EditPostResponse();
        response.setPostId(post.getPostId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setDateCreated(post.getDateTimeCreated().toString());
        return response;
    }



}