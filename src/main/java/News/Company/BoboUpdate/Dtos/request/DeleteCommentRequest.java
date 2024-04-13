package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;

@Data
public class DeleteCommentRequest {
    private String commentId;
    private String username;
}
