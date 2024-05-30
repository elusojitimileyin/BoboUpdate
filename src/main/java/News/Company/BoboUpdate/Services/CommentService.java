package News.Company.BoboUpdate.Services;

import News.Company.BoboUpdate.Data.Model.Comment;
import News.Company.BoboUpdate.Data.Model.User;
import News.Company.BoboUpdate.Dtos.request.CreateCommentRequest;
import News.Company.BoboUpdate.Dtos.request.DeleteCommentRequest;
import News.Company.BoboUpdate.Dtos.request.EditCommentRequest;
import News.Company.BoboUpdate.Dtos.response.CreateCommentResponse;
import News.Company.BoboUpdate.Dtos.response.DeleteCommentResponse;
import News.Company.BoboUpdate.Dtos.response.EditCommentResponse;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> getComment(String postId);

    Optional<Comment> getCommentsByUser(String postId, String userId);

    CreateCommentResponse createComment(CreateCommentRequest createCommentRequest);

    EditCommentResponse editComment(EditCommentRequest createCommentRequest);


    DeleteCommentResponse deleteComment(DeleteCommentRequest deleteCommentRequest);

    User findUserByUsername(String username);
}