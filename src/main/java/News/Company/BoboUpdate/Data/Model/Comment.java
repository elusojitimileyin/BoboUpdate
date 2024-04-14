package News.Company.BoboUpdate.Data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Comments")
public class Comment {
    @Id
    private String id;
    private String userId;
    private String comment;
    private String postId;
}
