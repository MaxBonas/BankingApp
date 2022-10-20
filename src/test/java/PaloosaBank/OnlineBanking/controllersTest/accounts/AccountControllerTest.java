package PaloosaBank.OnlineBanking.controllersTest.accounts;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.CreditCardRepository;

import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class AccountControllerTest {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

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

//    @Test
//    @DisplayName("Testing the method addCreditCard")
//    void addCreditCard_OK() throws Exception {
//
//
//
//        AccountDTO accountDTOTest = new AccountDTO(1L, accountHolderTest1.getId(), 523123D,
//                null, null, 110D, 0.12D );
//
//
//        String body = objectMapper.writeValueAsString(accountDTOTest);
//
//        MvcResult mvcResult = mockMvc.perform(post("/admin/credit_card").content(body).
//                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
//
////        assertTrue(mvcResult.getResponse().getContentAsString().contains("523123"));
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode tree = mapper.readTree(mvcResult.getResponse().getContentAsString());
//        JsonNode node = tree.get("id");
//        Long id = node.asLong();
//
//        assertTrue(accountHolderRepository.findById(id).isPresent());
//    }
}
