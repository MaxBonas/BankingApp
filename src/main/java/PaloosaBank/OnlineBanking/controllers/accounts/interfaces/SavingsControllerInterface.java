package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;

import java.util.List;

public interface SavingsControllerInterface {

//    Savings addSavings(AccountPostDTO savings);

    Savings updateSavings(Long id, AccountPostDTO savings);
}
