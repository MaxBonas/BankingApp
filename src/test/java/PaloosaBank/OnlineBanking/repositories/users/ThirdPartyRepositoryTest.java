package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ThirdPartyRepositoryTest {


    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    ThirdParty thirdPartyTest1;
    ThirdParty thirdPartyTest2;

    @BeforeEach
    void setUp() {

        thirdPartyTest1 = new ThirdParty("Test ThirdPartyUser", "test5@email.com", "TPpass3");
        thirdPartyRepository.save(thirdPartyTest1);

    }

    @Test
    @DisplayName("Check method findByName on ThirdParty")
    void findThirdPartyByName_OK () {

        assertTrue(thirdPartyRepository.findByName("Test ThirdPartyUser").isPresent());

    }

    @Test
    @DisplayName("Check method findByHashkey on ThirdParty")
    void findThirdPartyByHashkey_OK () {

        String hashkeyTest = thirdPartyTest1.getHashkey();
        assertTrue(thirdPartyRepository.findByHashkey(hashkeyTest).isPresent());

    }

//    Optional<ThirdParty> findByHashkey(String hashkey);
}
