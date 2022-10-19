package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface CreditCardControllerInterface {

//    CreditCard addCreditCard(AccountDTO creditCard);

    CreditCard updateCreditCard(Long id, AccountPostDTO creditCard);
}
