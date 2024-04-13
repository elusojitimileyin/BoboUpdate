package News.Company.BoboUpdate.Dtos.request;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String id;
    private String username;
    private String title;
    private String content;

}
