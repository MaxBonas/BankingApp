package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;

import java.util.List;

public interface AccountControllerInterface {

    Account addAccount(Account account);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
    Account updateAccount(Long id);
}
