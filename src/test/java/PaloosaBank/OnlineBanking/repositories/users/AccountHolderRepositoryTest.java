package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountHolderRepositoryTest {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    AccountHolder accountHolderTest1;
    AccountHolder accountHolderTest2;
    @BeforeEach
    void setUp() {

        accountHolderTest1 = new AccountHolder("Test May Lord", "test7@email.com", "testpass5",
                LocalDate.of(1203, 3, 22),
                new Address("Test Anselm Clave 7", "Test Corbera de Llobregat", "Test 08757"),
                new Address("Test Carrer Caceres 26", "Test Barcelona", "Test 08021"));
        accountHolderRepository.save(accountHolderTest1);

        accountHolderTest2 = new AccountHolder("Test Kant BeRight", "test6@email.com", "tsetpass6",
                LocalDate.of(2010, 1, 24),
                new Address("Test Crisol ave. 365", "Test New York", "Test 46266"),
                null);
        accountHolderRepository.save(accountHolderTest2);

    }

    @Test
    @DisplayName("Check method findByName on AccountHolder")
    void findAccountHolderByName_OK () {

        assertTrue(accountHolderRepository.findByName("Test May Lord").isPresent());

    }

    @Test
    @DisplayName("Check method findByDateOfBirth on AccountHolder")
    void findAccountHolderByDateOfBirth_OK () {

        LocalDate birthTest = accountHolderTest1.getDateOfBirth();
        assertEquals(accountHolderRepository.findByDateOfBirth(birthTest).get(0).getEmail(), accountHolderTest1.getEmail());
        System.out.println(accountHolderRepository.findByDateOfBirth(birthTest).get(0).getEmail());
    }


}
