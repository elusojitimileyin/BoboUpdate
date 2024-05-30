package News.Company.BoboUpdate.Dtos.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreatePostResponse {
    private String postId;
    private String title;
    private String content;
     private LocalDateTime dateTimeCreated = LocalDateTime.now();
}

