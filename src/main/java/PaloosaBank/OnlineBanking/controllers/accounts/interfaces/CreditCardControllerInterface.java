package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface CreditCardControllerInterface {

    CreditCard addCreditCard(CreditCard creditCard);
    CreditCard getCreditCardById(Long id);
    List<CreditCard> getAllCreditCards();
    CreditCard updateCreditCard(Long id);
}
