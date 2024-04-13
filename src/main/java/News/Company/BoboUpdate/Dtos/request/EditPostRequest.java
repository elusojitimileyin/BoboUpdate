package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;

@Data
public class EditPostRequest {
    private String title;
    private String username;
    private String content;
    public String PostId;
}
