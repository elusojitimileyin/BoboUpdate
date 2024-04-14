package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.request.DeletePostRequest;
import News.Company.BoboUpdate.Dtos.request.EditPostRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import News.Company.BoboUpdate.Dtos.response.DeletePostResponse;
import News.Company.BoboUpdate.Dtos.response.EditPostResponse;

import java.util.List;

public interface PostService {
    CreatePostResponse createPost(CreatePostRequest createPostRequest);

    EditPostResponse edit(EditPostRequest editPostRequest);

    DeletePostResponse delete(DeletePostRequest deletePostRequest);

    User findUserBy(String username);

    List<Post> getUserPost(String username);


}
