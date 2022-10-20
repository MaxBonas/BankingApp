package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    Admin adminTest1;
    Admin adminTest2;

    @BeforeEach
    void setUp() {

        adminTest1 = new Admin("Test AdminUser");
        adminRepository.save(adminTest1);

    }

    @Test
    @DisplayName("Check method findByName on Admin")
    void findAdminByName_OK () {

        assertTrue(adminRepository.findByName("Test AdminUser").isPresent());

    }
}
