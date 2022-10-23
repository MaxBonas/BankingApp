package PaloosaBank.OnlineBanking.controllersTest;

import PaloosaBank.OnlineBanking.DTOs.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.TransferPostDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.repositories.TransferRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.*;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    AccountHolder accountHolderTest1;
    AccountHolder accountHolderTest2;
    Checking accountTest1;
    Checking accountTest2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        accountHolderTest1 = new AccountHolder("Test May Lord", "test7@email.com", "testpass5",
                LocalDate.of(1989, 3, 22),
                new Address("Test Anselm Clave 7", "Test Corbera de Llobregat", "Test 08757"),
                new Address("Test Carrer Caceres 26", "Test Barcelona", "Test 08021"));
        accountHolderRepository.save(accountHolderTest1);

        accountHolderTest2 = new AccountHolder("Test Kant BeRight", "test6@email.com", "tsetpass6",
                LocalDate.of(2010, 1, 24),
                new Address("Test Crisol ave. 365", "Test New York", "Test 46266"),
                null);
        accountHolderRepository.save(accountHolderTest2);
        accountHolderRepository.save(accountHolderTest2);

        accountTest1 = new Checking(new Money(BigDecimal.valueOf(1000.53)), accountHolderTest1, accountHolderTest2);
        checkingRepository.save(accountTest1);

        accountTest2 = new Checking(new Money(BigDecimal.valueOf(2000000.00)), accountHolderTest2, accountHolderTest1);
        checkingRepository.save(accountTest2);
    }

    @Test
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "reduce balance of the senderAccount correctly")
    void transferAccountHolderAnyAccountReduce_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(),
                new BigDecimal("500.52"));
    }

    @Test
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "increments balance of the receiverAccount correctly")
    void transferAccountHolderAnyAccountIncrements_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals(checkingRepository.findById(accountTest2.getId()).get().getBalance().getAmount(),
                new BigDecimal("2500000.01"));
    }

    @Test
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "creates a new Transfer object")
    void transferAccountHolderAnyAccountAddTransfer_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        Transfer transferTest = transferRepository.findByTransferTime(LocalTime.now()).get(0);

        assertEquals(transferTest.getAmount(), (transferPostDTO.getAmount()));
    }

    @Test
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "sends ResponseStatus and cancel transfer if the secretKey is wrong.")
    void transferAccountHolderAnyAccountWrongSecretKey_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), "WrongSecretKey");

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(),
                new BigDecimal("1000.53"));
    }

    @Test
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "sends ResponseStatus and cancel transfer if the balance of the sender < than the transfer amount.")
    void transferAccountHolderAnyAccountNotEnoughFounds_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("20000.23"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(),
                new BigDecimal("1000.53"));
    }

    @Test
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "sends ResponseStatus and cancel transfer if the Account is FROZEN or INACTIVE.")
    void transferAccountHolderAnyAccountFrozenOrInactive_OK() throws Exception {

        accountTest1.setStatus(Status.FROZEN);
        checkingRepository.save(accountTest1);
        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(),
                new BigDecimal("1000.53"));
    }

    @Test  // BONUS requirement!
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "sends ResponseStatus, FREEZE Account and Cancel transfer if the method checkFraudLessThanSecond if is activated.")
    void transferAccountHolderAnyAccountCheckFraudLessSecond_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        TransferPostDTO transferPostDTO2 = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("100.00"), accountTest1.getSecretKey());

        String body2 = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult2 = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body2).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isTooEarly()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(),
                new BigDecimal("500.52"));
        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getStatus(), Status.FROZEN);
    }

    @Test  // BONUS requirement!
    @DisplayName("Testing that the method transferAccountHolderAnyAccount from AccountHolder " +
            "sends ResponseStatus, FREEZE Account and Cancel transfer if the method checkFraudTooMuch24h if is activated.")
    void transferAccountHolderAnyAccountCheckFraudTooMuch24h_OK() throws Exception {

        Transfer transfer1 = new Transfer(accountTest1, accountHolderTest2.getName(), accountTest1.getPrimaryOwner(),
                BigDecimal.valueOf(3.00));
        transfer1.setTransferDate(LocalDate.of(1973, 11, 20));
        transferRepository.save(transfer1);
        Transfer transfer2 = new Transfer(accountTest1, "Bar Tolo S.A.", accountTest1.getPrimaryOwner(),
                BigDecimal.valueOf(3.45));
        transfer2.setTransferDate(LocalDate.of(1973, 11, 20));
        transferRepository.save(transfer2);

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.01"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(),
                new BigDecimal("1000.53"));

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getStatus(), Status.FROZEN);
    }

    @Test
    @DisplayName("Testing that the method getBalanceAccountAccountHolder from AccountHolder returns balance correctly.")
    void getBalanceAccountAccountHolder_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/account_holder/check_balance_account/" + accountTest1.getId().toString()).
                        param("secretKey", accountTest1.getSecretKey()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.53"));
    }

    @Test
    @DisplayName("Testing that the method getBalanceAccountAccountHolder from AccountHolder" +
            "sends ResponseStatus and cancel showing balance if the secretKey is wrong.")
    void getBalanceAccountAccountHolderWrongSecretKey_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        get("/account_holder/check_balance_account/" + accountTest1.getId().toString()).
                                param("secretKey", "WrongSecretKey").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable()).andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().contains("1000.53"));
    }

    @Test
    @DisplayName("Testing that the method addAccount from Account Holder creates a Checking Account correctly")
    void postCheckingFromHolder_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                52312D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/account_holder/account").
                param("typeAccount", "CHECKING").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        Checking checkingTest = checkingRepository.findByBalance
                (new Money(BigDecimal.valueOf(52312D))).get(0);
        assertEquals(checkingTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));
    }

    @Test
    @DisplayName("Testing that the method addAccount from Account Holder creates a Credit Card Account correctly")
    void postCreditCardFromHolder_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                5231211D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/account_holder/account").
                param("typeAccount", "CREDITCARD").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        CreditCard creditCardTest = creditCardRepository.findByBalance
                (new Money(BigDecimal.valueOf(5231211D))).get(0);
        assertEquals(creditCardTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));
    }

    @Test
    @DisplayName("Testing that the method addAccount from Account Holder creates a Savings Account correctly")
    void postSavingsFromHolder_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                5231254D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/account_holder/account").
                param("typeAccount", "SAVINGS").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        Savings savingsTest = savingsRepository.findByBalance
                (new Money(BigDecimal.valueOf(5231254D))).get(0);
        assertEquals(savingsTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));

    }

    @Test
    @DisplayName("Testing that the method addAccount from Account Holder creates a Students Checking Account correctly")
    void postStudentsCheckingFromHolder_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                5231277D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/account_holder/account").
                param("typeAccount", "STUDENTSCHECKING").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        StudentsChecking studentsCheckingTest = studentsCheckingRepository.findByBalance
                (new Money(BigDecimal.valueOf(5231277D))).get(0);
        assertEquals(studentsCheckingTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));
    }

    @Test
    @DisplayName("Testing that the method addAccount from Account Holder creates a Students Checking Account " +
            "instead of a Checking if the Primary Owner is under 24 correctly")
    void postStudentsCheckingFromHolderIf24_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest2.getId(), accountHolderTest1.getId(),
                5231271D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/account_holder/account").
                param("typeAccount", "CHECKING").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        StudentsChecking studentsCheckingTest = studentsCheckingRepository.findByBalance
                (new Money(BigDecimal.valueOf(5231271D))).get(0);
        assertEquals(studentsCheckingTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));

    }

    @Test
    @DisplayName("Testing that the method addAccount from Account Holder creates an Account setting" +
            "the Status INACTIVE waiting validation from Admin correctly")
    void postAccountFromHolderINACTIVE_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest2.getId(), accountHolderTest1.getId(),
                5231279D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/account_holder/account").
                param("typeAccount", "CHECKING").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        StudentsChecking studentsCheckingTest = studentsCheckingRepository.findByBalance
                (new Money(BigDecimal.valueOf(5231279D))).get(0);
        assertEquals(studentsCheckingTest.getStatus(), Status.INACTIVE);
    }
}
