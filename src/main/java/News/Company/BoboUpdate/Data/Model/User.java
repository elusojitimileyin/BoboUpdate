package News.Company.BoboUpdate.Data.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("Users")
@RequiredArgsConstructor
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @DBRef
    private List<Post> posts = new ArrayList<>();
    private boolean isLoggedIn = true;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    private LocalDateTime dateUpdated = LocalDateTime.now();

}
