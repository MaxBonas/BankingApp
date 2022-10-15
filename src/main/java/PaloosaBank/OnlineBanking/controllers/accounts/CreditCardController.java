package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.CreditCardControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditCardController implements CreditCardControllerInterface {

    @Autowired
    CreditCardServiceInterface creditCardServiceInterface;

    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {
        return null;
    }

    @Override
    public CreditCard getCreditCardById(Long id) {
        return null;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return null;
    }

    @Override
    public CreditCard updateCreditCard(Long id, CreditCard creditCard) {
        return null;
    }
}
