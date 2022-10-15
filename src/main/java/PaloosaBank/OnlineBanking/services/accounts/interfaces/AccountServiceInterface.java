package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Account;

import java.util.List;

public interface AccountServiceInterface {

    Account addAccount(Account account);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
    Account updateAccount(Long id);
}
