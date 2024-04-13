package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;

@Data
public class EditCommentRequest {
    private String commentId;
    private String content;
    private String username;
    private String postId;
}