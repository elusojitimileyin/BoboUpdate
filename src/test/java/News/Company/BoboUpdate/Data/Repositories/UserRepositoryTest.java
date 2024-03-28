package News.Company.BoboUpdate.Data.Repositories;

import News.Company.BoboUpdate.Data.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach

    @Test
    void  saveTest(){
        User user = new User();
        userRepository.save(user);
        assertEquals(1,userRepository.count());

    }
}