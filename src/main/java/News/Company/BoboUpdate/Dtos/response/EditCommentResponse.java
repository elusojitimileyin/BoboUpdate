package News.Company.BoboUpdate.Dtos.response;
import lombok.Data;

@Data
public class EditCommentResponse {
    private String commentId;
    private String content;
    private String username;
    private String postId;

}