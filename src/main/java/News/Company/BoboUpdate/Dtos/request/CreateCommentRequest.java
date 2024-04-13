package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private String username;
    private String content;
    private String postId;
}
