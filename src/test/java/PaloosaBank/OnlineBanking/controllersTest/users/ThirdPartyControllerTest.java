package PaloosaBank.OnlineBanking.controllersTest.users;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.repositories.accounts.*;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartyControllerTest {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    AccountHolder accountHolderTest1;
    AccountHolder accountHolderTest2;
    Checking accountTest1;
    Checking accountTest2;

    ThirdParty thirdPartyTest;

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

        thirdPartyTest = new ThirdParty("Test S.A.");
        thirdPartyRepository.save(thirdPartyTest);
    }


    @Test
    @DisplayName("Testing the method patchThirdPartyAnyAccountBalance from ThirdParty")
    void patchThirdPartyAnyAccountBalance_OK() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                patch("/third_party/reduce_balance_account/" + accountTest1.getId().toString()).
                        param("amount", "100.00").header("hashkey", thirdPartyTest.getHashkey()).
                        contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("900.53"));
    }

}
