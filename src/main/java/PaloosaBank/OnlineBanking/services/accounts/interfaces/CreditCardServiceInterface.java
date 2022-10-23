package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface CreditCardServiceInterface {

    CreditCard getCreditCardById(Long id);
    List<CreditCard> getAllCreditCards();
}
