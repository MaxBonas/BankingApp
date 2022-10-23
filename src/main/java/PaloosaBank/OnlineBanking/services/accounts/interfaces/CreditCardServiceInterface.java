package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface CreditCardServiceInterface {

    CreditCard getCreditCardById(Long id);
    List<CreditCard> getAllCreditCards();
    List<CreditCard> findByBalance(Money balance); // Just for testing
}
