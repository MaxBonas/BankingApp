package PaloosaBank.OnlineBanking.controllersTest;

import PaloosaBank.OnlineBanking.DTOs.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.enums.TypeAccount;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositories.users.AdminRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
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
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
    // I had to put the Mapper like this to handle LocalDates in the Test.

    AccountHolder accountHolderTest1;
    AccountHolder accountHolderTest2;
    AccountHolder accountHolderTest3;
    Admin adminTest1;
    Admin adminTest2;
    ThirdParty thirdPartyTest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolderTest1 = new AccountHolder("Test May Lord", "test7@email.com", "testpass5", LocalDate.of(1989, 3, 22),
                new Address("Test Anselm Clave 7", "Test Corbera de Llobregat", "Test 08757"),
                new Address("Test Carrer Caceres 26", "Test Barcelona", "Test 08021"));
        accountHolderRepository.save(accountHolderTest1);

        accountHolderTest2 = new AccountHolder("Test Kant BeRight", "test6@email.com", "tsetpass6", LocalDate.of(2010, 1, 24),
                new Address("Test Crisol ave. 365", "Test New York", "Test 46266"),
                null);
        accountHolderRepository.save(accountHolderTest2);

        adminTest1 = new Admin("Test AdminUser", "test5@email.com", "adminpass3");
        adminRepository.save(adminTest1);
    }

    //-----------POST TESTS--------------
    @Test
    @DisplayName("Testing the method addAdmin by Admin")
    void postAdminByAdmin_OK() throws Exception {

        adminTest2 = new Admin("Test2 AdminUser", "test3@email.com", "adminpass2");
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

    @Test
    @DisplayName("Testing the method addThirdParty by Admin")
    void postThirdPartyByAdmin_OK() throws Exception {

        thirdPartyTest = new ThirdParty("Test ThirdPartyUser", "TestThirdParty@email.com", "hgtfuytf5476");
        thirdPartyTest.setId(100L);

        String body = objectMapper.writeValueAsString(thirdPartyTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/third_party").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(thirdPartyRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing the method addAccountHolder by Admin")
    void postAccountHolderByAdmin_OK() throws Exception {

        accountHolderTest3 = new AccountHolder("Test Testy", "test@email.com","jghfjgf5465",
                LocalDate.of(2012, 3, 21), new Address("Test Basch ave. 365",
                "Test Old York", "Test 48988"), null);
        accountHolderTest3.setId(100L);

        String body = objectMapper.writeValueAsString(accountHolderTest3); // TODO: no acepta LocalDate?

        MvcResult mvcResult = mockMvc.perform(post("/admin/account_holder").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = tree.get("id");
        Long id = node.asLong();

        assertTrue(accountHolderRepository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Testing that the method addAccount from Admin creates a Checking Account correctly")
    void postCheckingFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),523123D,
                null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/account").
                param("typeAccount", "CHECKING").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertFalse(checkingRepository.findByBalance(new Money(BigDecimal.valueOf(523123D))).isEmpty());
    }

    @Test
    @DisplayName("Testing that the method addAccount from Admin creates StudentsChecking " +
            "when owner is under 24 instead a Checking.")
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
    @DisplayName("Testing the method addAccount from Admin creates a Checking Account correctly")
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
    @DisplayName("Testing the method addAccount from Admin creates a Checking Account correctly")
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
    @DisplayName("Testing the method addAccount from Admin creates a Checking Account correctly")
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


    //    TODO AccountHolder addAccountHolder(); // Con un error

    //------------------GET TESTS--------------  // TODO no se hacerlos

    //    Admin getAdminById(Long id);
//    List<Admin> getAllAdmins();
//    Admin updateAdmin(Long id, Admin admin);
//

    //    ThirdParty getThirdPartyById(Long id);
//    List<ThirdParty> adminGetAllThirdPartys();
//    ThirdParty updateThirdParty();

//    AccountHolder getAccountHolderById(Long id);
//    List<AccountHolder> adminGetAllAccountHolders();
//    AccountHolder updateAccountHolder();

    //    Account getAccountById(Long id);
//    List<Account> getAllAccounts();

    //------------------UPDATE TESTS-------------------



    //-----------------DELETE TESTS-----------------

    //    User deleteUser();
    //    Account deleteAccountById(Long id);

    //-----------------PATCH TESTS------------------

    //    Account patchAdminAnyAccountBalance(Long accountId, BigDecimal amount);
//    Account patchStatusAccount (Long id);





//

//



}
