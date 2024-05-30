package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {


    Optional<Comment> findByPostId( String postId);
}
