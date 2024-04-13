package News.Company.BoboUpdate.Services;


import News.Company.BoboUpdate.BoboUpdateException.ViewAlreadyExistException;
import News.Company.BoboUpdate.Data.Model.Like;
import News.Company.BoboUpdate.Data.Repositories.LikeRepository;
import News.Company.BoboUpdate.Dtos.request.CreatePostRequest;
import News.Company.BoboUpdate.Dtos.response.CreatePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;



@Service
public class LikeServiceImp implements LikeService{



    @Autowired
    private LikeRepository likeRepository;

    @Override
    public CreatePostResponse likePost(CreatePostRequest createPostRequest) {
            if (!checkIfLikeAlreadyExist(createPostRequest)) {
   return (CreatePostResponse) likeRepository.findAll();
            } else {
                throw new ViewAlreadyExistException("View already exists");
            }
        }

        private boolean checkIfLikeAlreadyExist(CreatePostRequest createPostRequest) {
            Optional<Like> likes =  likeRepository.findById(createPostRequest.getId());
            return likes.isPresent();
        }
}
