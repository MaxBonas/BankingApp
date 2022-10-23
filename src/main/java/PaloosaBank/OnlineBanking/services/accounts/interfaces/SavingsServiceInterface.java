package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;

import java.util.List;

public interface SavingsServiceInterface {

    Savings getSavingsById(Long id);
    List<Savings> getAllSavings();
    Savings updateSavings(Long id, AccountPostDTO savings);
}
