package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
