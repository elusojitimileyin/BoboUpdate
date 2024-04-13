package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;

public interface ViewService {
    CreatePostResponse viewPost(CreatePostRequest createPostRequest);
}
