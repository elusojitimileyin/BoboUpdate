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

    List<Comment> getComment(String postId);

    List<Comment> getCommentsByUser(String postId, String userId);

    CreateCommentResponse createComment(CreateCommentRequest createCommentRequest);

    EditCommentResponse editComment(EditCommentRequest createCommentRequest);


    DeleteCommentResponse deleteComment(DeleteCommentRequest deleteCommentRequest);

    User findUserByUsername(String username);
}