package PaloosaBank.OnlineBanking.controllersTest.users;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.entities.users.User;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {
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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolderTest1 = new AccountHolder("Test May Lord", LocalDate.of(1989, 3, 22),
                new Address("Test Anselm Clave 7", "Test Corbera de Llobregat", "Test 08757"),
                new Address("Test Carrer Caceres 26", "Test Barcelona", "Test 08021"));
        accountHolderRepository.save(accountHolderTest1);
    }

    @Test
    @DisplayName("Testing the method addChecking from Admin")
    void postCheckingFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(1L, accountHolderTest1.getId(), 523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/checking_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(checkingRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addCreditCard from Admin")
    void postCreditCardFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(1L, accountHolderTest1.getId(), 523123D,
                null, null, 110D, 0.14D );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/credit_card").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

//        assertTrue(mvcResult.getResponse().getContentAsString().contains("523123")); // One way, less efficient to test

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(creditCardRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addSavings from Admin")
    void postSavingsFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(1L, accountHolderTest1.getId(), 523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/savings").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

//        assertTrue(mvcResult.getResponse().getContentAsString().contains("523123")); // One way, less efficient to test

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(savingsRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addStudentsChecking from Admin")
    void postStudentsCheckingFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(1L, accountHolderTest1.getId(), 523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/students_checking").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

//        assertTrue(mvcResult.getResponse().getContentAsString().contains("523123")); // One way, less efficient to test

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(studentsCheckingRepository.findById(id).isPresent());
    }

//    Admin addAdmin(Admin admin);
//    Admin getAdminById(Long id);
//    List<Admin> getAllAdmins();
//    Admin updateAdmin(Long id, Admin admin);
//
//    ThirdParty addThirdParty();
//    ThirdParty getThirdPartyById(Long id);
//    List<ThirdParty> adminGetAllThirdPartys();
//    ThirdParty updateThirdParty();
//    AccountHolder addAccountHolder();
//    ThirdParty getThirdPartyById(Long id);
//    List<AccountHolder> adminGetAllAccountHolders();
//    AccountHolder updateAccountHolder();
//    User deleteUser();
//
//    Account addAccount();
//    Account adminGetAccountById(Long id);
//    List<Account> adminGetAllAccounts();
//    Account updateAccount();
//    Account deleteAccount();
//    Account patchAdminAnyAccountBalance(Long accountId, BigDecimal balance);
//Account patchStatusAccount (Long id);


}
