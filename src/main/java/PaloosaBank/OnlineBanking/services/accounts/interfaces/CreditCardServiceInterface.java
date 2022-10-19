package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface CreditCardServiceInterface {

    CreditCard addCreditCard(AccountPostDTO creditCard);
    CreditCard getCreditCardById(Long id);
    List<CreditCard> getAllCreditCards();
    CreditCard updateCreditCard(Long id, AccountPostDTO creditCard);
}
