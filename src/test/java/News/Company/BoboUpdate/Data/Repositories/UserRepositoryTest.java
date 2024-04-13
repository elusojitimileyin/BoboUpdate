package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testThatUserCanSave(){
        User user = new User();
        userRepository.save(user);
        assertThat(userRepository.count(), is(1L));

    }

}