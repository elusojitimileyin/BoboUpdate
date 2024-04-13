package News.Company.BoboUpdate.Data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Likes")
public class Like {
    @Id
    private String  id;
    private User findBy;
    private String postId;
    private LocalDateTime timeOfView = LocalDateTime.now();
}