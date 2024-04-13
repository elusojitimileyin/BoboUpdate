package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.Comment;
import News.Company.BoboUpdate.Data.Model.Post;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;

public interface LikeService {
    CreatePostResponse likePost(CreatePostRequest createPostRequest);
}
