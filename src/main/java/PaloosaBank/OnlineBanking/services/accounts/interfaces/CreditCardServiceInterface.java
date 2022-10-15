package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface CreditCardServiceInterface {

    CreditCard addCreditCard(CreditCard creditCard);
    CreditCard getCreditCardById(Long id);
    List<CreditCard> getAllCreditCards();
    CreditCard updateCreditCard(Long id, CreditCard creditCard);
}
