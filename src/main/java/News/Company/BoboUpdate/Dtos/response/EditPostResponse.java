package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditPostResponse {
    public String postId;
    private String title;
    private String content;
    private String dateCreated;
}
