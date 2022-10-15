package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;

import java.util.List;

public interface SavingsControllerInterface {

    Savings addSavings(Savings savings);
    Savings getSavingsById(Long id);
    List<Savings> getAllSavings();
    Savings updateSavings(Long id, Savings savings);
}
