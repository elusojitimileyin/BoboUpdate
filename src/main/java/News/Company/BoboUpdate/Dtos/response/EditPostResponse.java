package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;

@Data
public class EditPostResponse {
    public String postId;
    private String title;
    private String content;
    private String dateCreated;
}
