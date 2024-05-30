package News.Company.BoboUpdate.Controller;

import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Utils.BoboUpdateException;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.request.DeletePostRequest;
import News.Company.BoboUpdate.Dtos.request.EditPostRequest;
import News.Company.BoboUpdate.Dtos.response.ApiResponse;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import News.Company.BoboUpdate.Services.PostService;
import News.Company.BoboUpdate.Services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody CreatePostRequest createPostRequest) {
        try {
            CreatePostResponse createPostResponse = postService.createPost(createPostRequest);
            return new ResponseEntity<>(createPostResponse, CREATED);
        } catch (BoboUpdateException message) {
            return new ResponseEntity<>(message.getMessage(), BAD_REQUEST);
        }
    }

    @PutMapping("/edit-post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editPostRequest) {
        try {
            var result = postService.editPost(editPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest deletePostRequest) {
        try {
            var result = postService.deletePost(deletePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BoboUpdateException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/get_post")
    public ResponseEntity<?>getAllPost() {
        try {
            List<Post> result = postService.getAllUsersPost();
            return new ResponseEntity<>(new ApiResponse(true,result ), CREATED);
        } catch (BoboUpdateException  message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    
}
