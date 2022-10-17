package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface AccountControllerInterface {
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
}
