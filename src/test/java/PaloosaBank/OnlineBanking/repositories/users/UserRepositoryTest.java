package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    Admin adminTest1;
    ThirdParty thirdPartyTest1;

    @BeforeEach
    void setUp() {

        adminTest1 = new Admin("Test AdminUser", "test5@email.com", "adminpass3");
        adminRepository.save(adminTest1);

        thirdPartyTest1 = new ThirdParty("Test ThirdPartyUser", "test9@email.com", "TPpass3");
        thirdPartyRepository.save(thirdPartyTest1);

    }

    @Test
    @DisplayName("Check method findByName on any User")
    void findAnyUserByName_OK () {

        assertTrue(userRepository.findByName("Test AdminUser").isPresent());
        assertTrue(userRepository.findByName("Test ThirdPartyUser").isPresent());

    }
}
