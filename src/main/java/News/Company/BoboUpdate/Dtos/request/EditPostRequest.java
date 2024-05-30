package News.Company.BoboUpdate.Dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditPostRequest {
    private String title;
    private String username;
    private String content;
    public String PostId;
}
