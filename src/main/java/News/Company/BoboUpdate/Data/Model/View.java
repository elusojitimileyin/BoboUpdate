package News.Company.BoboUpdate.Data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document("Views")
public class View {
    @Id
    private String id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime timeOfView = LocalDateTime.now();

}
