package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Savings;

import java.util.List;

public interface SavingsServiceInterface {

    Savings addSavings(Savings savings);
    Savings getSavingsById(Long id);
    List<Savings> getAllSavings();
    Savings updateSavings(Long id);
}
