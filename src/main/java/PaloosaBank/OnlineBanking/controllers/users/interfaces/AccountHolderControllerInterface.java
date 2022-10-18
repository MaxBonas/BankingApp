package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderControllerInterface {

    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);
    List<AccountHolder> getAllAccountHolders();
    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    List<Account> transferAccountHolderAnyAccount(Long accountOutId, Long accountInId, BigDecimal balance, String secretKey);
}
