package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
