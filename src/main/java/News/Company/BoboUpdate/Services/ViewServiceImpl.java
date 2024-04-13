package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.BoboUpdateException.TitleExistsException;
import News.Company.BoboUpdate.BoboUpdateException.ViewAlreadyExistException;
import News.Company.BoboUpdate.Data.Model.View;
import News.Company.BoboUpdate.Data.Repositories.ViewRepository;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static News.Company.BoboUpdate.Utils.Mapper.map;

@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private ViewRepository viewRepository;

    @Override
    public CreatePostResponse viewPost(CreatePostRequest createPostRequest) {
        if (!checkIfViewAlreadyExist(createPostRequest)) {
            return getCreatePostResponse(createPostRequest, viewRepository.existsByTitleIgnoreCase(createPostRequest.getTitle()));
        } else {
            throw new ViewAlreadyExistException("View already exists");
        }
    }

    static CreatePostResponse getCreatePostResponse(CreatePostRequest createPostRequest, boolean b) {
        View view = map(createPostRequest);
        if (b) {
            throw new TitleExistsException("Title already exists");
        }


        CreatePostResponse response = new CreatePostResponse();
        response.setTitle(view.getTitle());
        response.setContent(view.getContent());
        response.setPostId(view.getId());
        return response;
    }

    private boolean checkIfViewAlreadyExist(CreatePostRequest createPostRequest) {
        List<View> views = viewRepository.findAllByUsernameAndTitleAndContent(
                createPostRequest.getUsername(),
                createPostRequest.getTitle(),
                createPostRequest.getContent());
        return !views.isEmpty();
    }

}
