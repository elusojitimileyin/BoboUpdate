package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository extends MongoRepository<View,String> {
    List<View> findAllByUsernameAndTitleAndContent(String username, String title, String content);

    boolean existsByTitleIgnoreCase(String title);
}

