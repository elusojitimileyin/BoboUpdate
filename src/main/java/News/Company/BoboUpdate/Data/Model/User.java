package News.Company.BoboUpdate.Data.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    private String username;
    private String password;
    @DBRef
    private List<Post> posts;


}
