package PaloosaBank.OnlineBanking.controllersTest.accounts;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@SpringBootTest
public class CheckingControllerTest {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingRepository checkingRepository;

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

//    Checking getCheckingById(Long id);
//    List<Checking> getAllCheckings();
//    Checking updateChecking(Long id, AccountPostDTO checking);

}
