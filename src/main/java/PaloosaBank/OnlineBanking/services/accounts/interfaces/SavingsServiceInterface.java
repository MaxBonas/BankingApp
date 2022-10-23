package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;

import java.util.List;

public interface SavingsServiceInterface {

    Savings getSavingsById(Long id);
    List<Savings> getAllSavings();
    List<Savings> findByBalance(Money balance); // Just for testing
}
