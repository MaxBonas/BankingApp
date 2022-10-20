package PaloosaBank.OnlineBanking.controllersTest.users;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AdminRepository;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    AdminRepository adminRepository;
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
    Admin adminTest1;
    Admin adminTest2;

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

        adminTest1 = new Admin("Test AdminUser");
        adminRepository.save(adminTest1);
    }

    @Test
    @DisplayName("Testing the method addChecking from Admin")
    void postCheckingFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/checking_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        //assertTrue(mvcResult.getResponse().getContentAsString().contains("523123"));
        // One way, less efficient to test it

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(checkingRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing that the method addChecking from Admin creates StudentsChecking when owner is under 24")
    void postCheckingFromAdminCreateStudentsCheckingUnder24_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest2.getId(), accountHolderTest1.getId(), 523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/checking_account").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(studentsCheckingRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addCreditCard from Admin")
    void postCreditCardFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(), 523123D,
                null, null, 110D, 0.14D );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/credit_card").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(creditCardRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addSavings from Admin")
    void postSavingsFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest2.getId(), accountHolderTest1.getId(), 523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/savings").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(savingsRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addStudentsChecking from Admin")
    void postStudentsCheckingFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest2.getId(), accountHolderTest1.getId(), 523123D,
                null, null, null, null );

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/students_checking").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(studentsCheckingRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addAdmin")
    void postAdmin_OK() throws Exception {

        adminTest2 = new Admin("Test2 AdminUser");
        adminTest2.setId(100L);

        String body = objectMapper.writeValueAsString(adminTest2);

        MvcResult mvcResult = mockMvc.perform(post("/admin").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(adminRepository.findById(id).isPresent());
    }


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
