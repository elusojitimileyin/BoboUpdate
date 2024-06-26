package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByPostId(String postId);
}

