package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;

import java.util.List;

public interface SavingsControllerInterface {

    Savings addSavings(AccountDTO savings);
    Savings getSavingsById(Long id);
    List<Savings> getAllSavings();
    Savings updateSavings(Long id, AccountDTO savings);
}
