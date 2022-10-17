package PaloosaBank.OnlineBanking;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.CreditCardRepository;

import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                new Address("Anselm Clave 7", "Corbera de Llobregat", "08757"),
                new Address("Carrer Caceres 26", "Barcelona", "08021"));
        accountHolderRepository.save(accountHolderTest1);
    }

    @Test
    @DisplayName("Testing the method addCreditCard")
    void addCreditCard_OK() throws Exception {



        AccountDTO accountDTOTest = new AccountDTO(1L, accountHolderTest1.getId(), 5000D,
                null, null, null, null );


        String body = objectMapper.writeValueAsString(accountDTOTest);

        MvcResult mvcResult = mockMvc.perform(post("/credit_card").content(body).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
//        creditCardRepository.fi
        System.err.println(mvcResult.getResponse());

//        Long id = mvcResult.getResponse().
//        assertTrue(creditCardRepository.findById(id).isPresent());
    }
}
