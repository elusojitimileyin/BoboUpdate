package News.Company.BoboUpdate.Data.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Comments")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Comment {
    @Id
    private String postId;
    private String comment;
    private String username;
    private String content;
}
