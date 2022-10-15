package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.CreditCardControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController implements CreditCardControllerInterface {

    @Autowired
    CreditCardServiceInterface creditCardServiceInterface;
}
