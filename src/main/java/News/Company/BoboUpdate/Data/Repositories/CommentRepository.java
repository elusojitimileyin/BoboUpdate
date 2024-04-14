package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.Comment;
import News.Company.BoboUpdate.Data.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByPostId(String id);

    List<Comment> findByUserId(String id);

    Optional<Comment> findByPostIdAndUserId(String id, String userId);
}
