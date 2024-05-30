package News.Company.BoboUpdate.Data.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Document("Views")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class View {
    @Id
    private String postId;
    private LocalDateTime timeOfView = LocalDateTime.now();
    private Set<String> viewer = new HashSet<>();
}
