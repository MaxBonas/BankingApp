package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;

import java.util.List;

public interface AccountServiceInterface {

    Account getAccountById(Long id);
    List<Account> getAllAccounts();

    Account patchThirdPartyAnyAccountBalance(Long id, Money balance, String hashkey);
}
