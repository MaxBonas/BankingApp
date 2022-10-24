package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentsRepositoryTest {

    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


    AccountHolder accountHolderTest1;
    AccountHolder accountHolderTest2;
    StudentsChecking accountTest1;
    @BeforeEach
    void setUp() {

        accountHolderTest1 = new AccountHolder("Test May Lord", "test7@email.com", "testpass5", LocalDate.of(1989, 3, 22),
                new Address("Test Anselm Clave 7", "Test Corbera de Llobregat", "Test 08757"),
                new Address("Test Carrer Caceres 26", "Test Barcelona", "Test 08021"));
        accountHolderRepository.save(accountHolderTest1);

        accountHolderTest2 = new AccountHolder("Test Kant BeRight", "test6@email.com", "tsetpass6", LocalDate.of(2010, 1, 24),
                new Address("Test Crisol ave. 365", "Test New York", "Test 46266"),
                null);
        accountHolderRepository.save(accountHolderTest2);

        accountTest1 = new StudentsChecking(new Money(BigDecimal.valueOf(9988.77)), accountHolderTest1,
                accountHolderTest2);
        studentsCheckingRepository.save(accountTest1);
    }

    @Test
    @DisplayName("Check method findBySecretKey on StudentsChecking")
    void findStudentsCheckingBySecretKey_OK () {
        String secretKeyTest = accountTest1.getSecretKey();

        assertTrue(studentsCheckingRepository.findBySecretKey(secretKeyTest).isPresent());

    }
}
