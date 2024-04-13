package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.Comment;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Dtos.request.CreateCommentRequest;
import News.Company.BoboUpdate.Dtos.request.DeleteCommentRequest;
import News.Company.BoboUpdate.Dtos.request.EditCommentRequest;
import News.Company.BoboUpdate.Dtos.response.CreateCommentResponse;
import News.Company.BoboUpdate.Dtos.response.DeleteCommentResponse;
import News.Company.BoboUpdate.Dtos.response.EditCommentResponse;

import java.util.List;

public interface CommentService {

    User findUserBy(String username);

    List<Comment> getCommentBy(String postId);

    List<Comment> getCommentsByUser(String postId, String userId);

    CreateCommentResponse createCommentWith(CreateCommentRequest createCommentRequest);

    EditCommentResponse editCommentWith(EditCommentRequest createCommentRequest);


    DeleteCommentResponse deleteCommentWith(DeleteCommentRequest deleteCommentRequest);
}