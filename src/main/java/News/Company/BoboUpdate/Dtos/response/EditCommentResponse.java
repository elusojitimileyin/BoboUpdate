package News.Company.BoboUpdate.Dtos.response;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditCommentResponse {
    private String commentId;
    private String content;
    private String username;
    private String postId;

}