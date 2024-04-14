package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePostResponse {
    private String postId;
    private String title;
    private String content;
     private LocalDateTime dateCreated = LocalDateTime.now();
}

