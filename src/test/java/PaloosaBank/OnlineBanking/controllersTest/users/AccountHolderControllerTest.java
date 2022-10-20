package PaloosaBank.OnlineBanking.controllersTest.users;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;

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

        accountHolderTest1 = new AccountHolder("Test May Lord", LocalDate.of(1989, 3, 22),
                new Address("Test Anselm Clave 7", "Test Corbera de Llobregat", "Test 08757"),
                new Address("Test Carrer Caceres 26", "Test Barcelona", "Test 08021"));
        accountHolderRepository.save(accountHolderTest1);

        accountHolderTest2 = new AccountHolder("Test Kant BeRight", LocalDate.of(2010, 1, 24),
                new Address("Test Crisol ave. 365", "Test New York", "Test 46266"),
                null);
        accountHolderRepository.save(accountHolderTest2);

        accountTest1 = new Checking(new Money(BigDecimal.valueOf(1000.53)), accountHolderTest1, accountHolderTest2);
        checkingRepository.save(accountTest1);

        accountTest2 = new Checking(new Money(BigDecimal.valueOf(2000.00)), accountHolderTest2, accountHolderTest1);
        checkingRepository.save(accountTest2);
    }

    @Test
    @DisplayName("Testing the method transferAccountHolderAnyAccount from AccountHolder")
    void transferAccountHolderAnyAccount_OK() throws Exception {

        TransferPostDTO transferPostDTO = new TransferPostDTO(accountTest1.getId(), accountTest2.getId(),
                new BigDecimal("500.00"), accountTest1.getSecretKey());

        String body = objectMapper.writeValueAsString(transferPostDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/account_holder/transfer_amount_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals(checkingRepository.findById(accountTest1.getId()).get().getBalance().getAmount(), new BigDecimal("500.00"));
    }

    @Test
    @DisplayName("Testing the method getBalanceAccountAccountHolder from AccountHolder")
    void getBalanceAccountAccountHolder_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/account_holder/check_balance_account/" + accountTest1.getId().toString()).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.53"));
    }
}
