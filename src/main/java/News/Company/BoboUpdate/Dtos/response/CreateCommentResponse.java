package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;

@Data
public class CreateCommentResponse {
    private String username;
    private String content;
    private String PostId;
}
