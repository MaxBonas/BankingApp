package PaloosaBank.OnlineBanking.repositories;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransferRepositoryTests {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    TransferService transferService;

    AccountHolder accountHolderTest1;
    AccountHolder accountHolderTest2;
    Checking accountTest1;
    Checking accountTest2;
    Checking accountTest3;
    Checking accountTest4;
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

        accountTest1 = new Checking(new Money(BigDecimal.valueOf(1000.53)), accountHolderTest1, accountHolderTest2);
        checkingRepository.save(accountTest1);

        accountTest2 = new Checking(new Money(BigDecimal.valueOf(2000000.00)), accountHolderTest2, accountHolderTest1);
        checkingRepository.save(accountTest2);

        Transfer transfer1 = new Transfer(accountTest1, accountHolderTest2.getName(), accountTest1.getPrimaryOwner(),
                BigDecimal.valueOf(3230.00));
        transfer1.setTransferDate(LocalDate.of(1973, 11, 20));
        transfer1.setTransferTime(LocalTime.of(15, 12, 3));
        transferRepository.save(transfer1);
        Transfer transfer2 = new Transfer(accountTest2, "Bar Tolo S.A.", accountTest2.getPrimaryOwner(),
                BigDecimal.valueOf(250.45));
        transfer2.setTransferDate(LocalDate.of(1973, 11, 20));
        transferRepository.save(transfer2);
        Transfer transfer3 = new Transfer(accountTest1, accountHolderTest2.getName(), accountTest1.getPrimaryOwner(),
                BigDecimal.valueOf(1000.00));
        transfer3.setTransferDate(LocalDate.of(1973, 11, 20));
        transfer3.setTransferTime(LocalTime.of(15, 12, 3));
        transferRepository.save(transfer3);
        Transfer transfer4 = new Transfer(accountTest1, "Bar Memo S.A.", accountTest1.getPrimaryOwner(),
                BigDecimal.valueOf(1000.00));
        transfer4.setTransferDate(LocalDate.of(2000, 12, 20));
        transferRepository.save(transfer4);
    }

    @Test
    @DisplayName("Check method findBySenderAccountId of Transfer")
    void findBySenderAccountId_OK () {

        Transfer transferTest = transferRepository.findBySenderAccountId(accountTest1.getId()).get(0);
        assertEquals(3230.00d, transferTest.getAmount().doubleValue());

    }

    @Test
    @DisplayName("Check method findByPrimaryOwnerId of Transfer")
    void findByPrimaryOwnerId_OK () {

        Transfer transferTest = transferRepository.findByPrimaryOwnerId(accountTest1.getPrimaryOwner().getId()).get(0);
        assertEquals(3230.00d, transferTest.getAmount().doubleValue());

    }

    @Test
    @DisplayName("Check method findByAmount of Transfer")
    void findByAmount_OK () {

        Transfer transferTest = transferRepository.findByAmount(BigDecimal.valueOf(3230.00)).get(0);
        assertEquals(3230.00d, transferTest.getAmount().doubleValue());

    }

    @Test
    @DisplayName("Check method findByTransferDate of Transfer")
    void findByTransferDate_OK () {

        Transfer transferTest = transferRepository.findByTransferDate(LocalDate.of(1973, 11, 20)).get(0);
        assertEquals(3230.00d, transferTest.getAmount().doubleValue());

    }

    @Test
    @DisplayName("Check method findByTransferTime of Transfer")
    void findByTransferTime_OK () {

        Transfer transferTest = transferRepository.findByTransferTime(LocalTime.of(15, 12, 3)).get(0);
        assertEquals(3230.00d, transferTest.getAmount().doubleValue());

    }

    @Test
    @DisplayName("Check that the method max24HourAmount of Transfer get the MAX total daily spended ever " +
            "for an Account Holder")
    void max24HourAmount_OK () {

        assertEquals(4230.00d,
                transferRepository.max24HourAmount(accountTest1.getPrimaryOwner().getId()).getAmount().doubleValue());

    }
}
