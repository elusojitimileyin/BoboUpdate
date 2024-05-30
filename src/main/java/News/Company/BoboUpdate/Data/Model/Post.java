package News.Company.BoboUpdate.Data.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("posts")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Post {
    @Id
    private String postId;
    private String title;
    private String content;
    private LocalDateTime dateTimeCreated;

    private LocalDateTime dateTimeUpdated;
    @DBRef
    private List<View> views = new ArrayList<>();
    @DBRef
    private List<Comment> comments = new ArrayList<>();

}
