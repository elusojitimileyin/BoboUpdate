package News.Company.BoboUpdate.Data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    @DBRef
    private List<View> views;
    @DBRef
    private List<Comment> comments;
}