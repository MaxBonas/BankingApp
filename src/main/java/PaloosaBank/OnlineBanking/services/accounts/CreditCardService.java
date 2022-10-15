package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

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
    public CreditCard updateCreditCard(Long id) {
        return null;
    }
}
