package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
