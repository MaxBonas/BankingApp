package PaloosaBank.OnlineBanking.controllersTest;

import PaloosaBank.OnlineBanking.DTOs.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.repositories.accounts.*;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    AccountRepository accountRepository;
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
    ThirdParty thirdPartyTest2;
    Checking accountTest1;
    Checking accountTest2;
    Checking accountTest3;

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

        accountTest1 = new Checking(new Money(BigDecimal.valueOf(1000.53)), accountHolderTest1, accountHolderTest2);
        checkingRepository.save(accountTest1);

        accountTest2 = new Checking(new Money(BigDecimal.valueOf(2000000.00)), accountHolderTest2, accountHolderTest1);
        checkingRepository.save(accountTest2);

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

        MvcResult mvcResult = mockMvc.perform(post("/admin/admin").content(body).
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

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                52312D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/account").
                param("typeAccount", "CHECKING").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        Checking checkingTest = checkingRepository.findByBalance
                (new Money(BigDecimal.valueOf(52312D))).get(0);
        assertEquals(checkingTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));
    }

    @Test
    @DisplayName("Testing that the method addAccount from Admin creates a Students Checking Account " +
            "instead of a Checking if the Primary Owner is under 24 correctly")
    void postStudentsCheckingFromAdminIf24_OK() throws Exception {

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
    @DisplayName("Testing that the method addAccount from Admin creates a Credit Card Account correctly")
    void postCreditCardFromAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                5231211D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/admin/account").
                param("typeAccount", "CREDITCARD").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        CreditCard creditCardTest = creditCardRepository.findByBalance
                (new Money(BigDecimal.valueOf(5231211D))).get(0);
        assertEquals(creditCardTest.getBalance().getAmount().doubleValue(), (accountPostDTOTest.getBalance()));
    }

    @Test
    @DisplayName("Testing that the method addAccount from Admin creates a Savings Account correctly")
    void postSavingsFromAdmin_OK() throws Exception {

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
    @DisplayName("Testing that the method addAccount from Admin creates a Students Checking Account correctly")
    void postStudentsCheckingFromAdmin_OK() throws Exception {

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

    //------------------GET TESTS--------------

    @Test
    @DisplayName("Testing that the method getAdminById from Admin returns Admin correctly.")
    void getAdminById_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        get("/admin/" + adminTest1.getId().toString()).
                                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test AdminUser"));
    }

    @Test
    @DisplayName("Testing that the method getAllAdmins from Admin returns all Admins correctly.")
    void getAllAdmins_OK() throws Exception {

        adminTest2 = new Admin("Test AdminUser2", "test11@email.com", "adminpass4");
        adminRepository.save(adminTest2);
        MvcResult mvcResult = mockMvc.perform(
                get("/admin/admins").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test AdminUser") &&
                mvcResult.getResponse().getContentAsString().contains("Test AdminUser2"));
}

    @Test
    @DisplayName("Testing that the method getAccountHolderById from Admin returns AccountHolder correctly.")
    void getAccountHolderById_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/admin/account_holder/" + accountHolderTest1.getId().toString()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test May Lord"));
    }

    @Test
    @DisplayName("Testing that the method getAllAccountHolders from Admin returns all AccountHoldera correctly.")
    void getAllAccountHolders_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/admin/account_holders").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test May Lord") &&
                mvcResult.getResponse().getContentAsString().contains("Test Kant BeRight"));
    }

    @Test
    @DisplayName("Testing that the method getThirdPartyById from Admin returns ThirdParty correctly.")
    void getThirdPartyById_OK() throws Exception {

        thirdPartyTest = new ThirdParty("Test ThirdPartyUser1", "test12@email.com", "adminpass5");
        thirdPartyRepository.save(thirdPartyTest);
        MvcResult mvcResult = mockMvc.perform(
                get("/admin/third_part/" + adminTest1.getId().toString()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test AdminUser"));
    }

    @Test
    @DisplayName("Testing that the method getAllThirdPartys from Admin returns all ThirdPartys correctly.")
    void getAllThirdPartys_OK() throws Exception {

        thirdPartyTest2 = new ThirdParty("Test ThirdPartyUser2", "test13@email.com", "adminpass6");
        thirdPartyRepository.save(thirdPartyTest2);
        thirdPartyTest = new ThirdParty("Test ThirdPartyUser1", "test14@email.com", "adminpass7");
        thirdPartyRepository.save(thirdPartyTest);
        MvcResult mvcResult = mockMvc.perform(
                get("/admin/third_partys").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test ThirdPartyUser1") &&
                mvcResult.getResponse().getContentAsString().contains("Test ThirdPartyUser2"));
    }

    @Test
    @DisplayName("Testing that the method getAccountById from Admin returns Account correctly.")
    void getAccountById_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/admin/account/" + accountTest1.getId().toString()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.53"));
    }

    @Test
    @DisplayName("Testing that the method getAllThirdPartys from Admin returns all ThirdPartys correctly.")
    void getAllAccounts_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                get("/admin/accounts").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.53") &&
                mvcResult.getResponse().getContentAsString().contains("2000000.00"));
    }

    //-----------------DELETE TESTS-----------------

    @Test
    @DisplayName("Testing that the method deleteUserById from Admin deletes User correctly.")
    void deleteUserById_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                delete("/admin/user/" + adminTest1.getId().toString()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertFalse(adminRepository.findByName("Test AdminUser").isPresent());
    }

    @Test
    @DisplayName("Testing that the method deleteAccountById from Admin deletes Account correctly.")
    void deleteAccountById_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                delete("/admin/account/" + accountTest1.getId().toString()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertFalse(accountRepository.findById(accountTest1.getId()).isPresent());
    }

    //-----------------PATCH TESTS------------------

    @Test
    @DisplayName("Testing the method patchAdminAnyAccountBalance from Admin")
    void patchAnyAccountBalanceFromAdmin_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                patch("/admin/reduce_balance_account/" + accountTest1.getId().toString()).
                        param("amount", "100.00").
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountTest1.getPrimaryOwner().getName()));
    }

    @Test
    @DisplayName("Testing the method patchAdminAnyAccountStatus from Admin")
    void patchAnyAccountStatusFromAdmin_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                patch("/admin/change_status_account/" + accountTest1.getId().toString()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FROZEN"));
    }

    //------------------UPDATE TESTS-------------------

    @Test
    @DisplayName("Testing the method updateAdmin by Admin")
    void updateAdminByAdmin_OK() throws Exception {

        adminTest2 = new Admin("Test2 AdminUser", "test3@email.com", "adminpass2");

        String body = objectMapper.writeValueAsString(adminTest2);

        MvcResult mvcResult = mockMvc.perform(put("/admin/admin/" + adminTest1.getId().toString())
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals("Test2 AdminUser", adminRepository.findById(adminTest1.getId()).get().getName());
    }

    @Test
    @DisplayName("Testing the method updateThirdParty by Admin")
    void updateupdateThirdPartyByAdmin_OK() throws Exception {

        ThirdParty thirdPartyTest = new ThirdParty("Testtodesapear", "test32@Testtodesapear.com",
                "Testtodesapear");
        thirdPartyRepository.save(thirdPartyTest);

        thirdPartyTest2 = new ThirdParty("Test2 ThirdPartyUser", "test32@email.com", "adminpass22");

        String body = objectMapper.writeValueAsString(thirdPartyTest2);

        MvcResult mvcResult = mockMvc.perform(put("/admin/third_party/" + thirdPartyTest.getId().toString())
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals("Test2 ThirdPartyUser", thirdPartyRepository.findById(thirdPartyTest.getId()).get().getName());
    }

    @Test
    @DisplayName("Testing the method updateAccountHolder by Admin")
    void updateAccountHolderByAdmin_OK() throws Exception {

        accountHolderTest3 = new AccountHolder("Test Kant BeRight", "test56@email.com",
                "tsetpass6", LocalDate.of(2010, 1, 24),
                new Address("Test Crisol ave. 365", "Test New York", "Test 46266"),
                null);

        String body = objectMapper.writeValueAsString(accountHolderTest3);

        MvcResult mvcResult = mockMvc.perform(put("/admin/account_holder/" + accountHolderTest1.getId().toString())
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals("Test Kant BeRight", accountHolderRepository.findById(accountHolderTest1.getId()).get().getName());
    }

    @Test
    @DisplayName("Testing the method updateAnyAccountByAdmin by Admin")
    void updateAnyAccountByAdmin_OK() throws Exception {

        AccountPostDTO accountPostDTOTest = new AccountPostDTO(accountHolderTest1.getId(), accountHolderTest2.getId(),
                5231254D, null, null, null, null);

        String body = objectMapper.writeValueAsString(accountPostDTOTest);

        MvcResult mvcResult = mockMvc.perform(put("/admin/account/" + accountTest1.getId().toString())
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertEquals(5231254D, accountRepository.findById(accountTest1.getId()).get().getBalance().getAmount().doubleValue());
    }
}
