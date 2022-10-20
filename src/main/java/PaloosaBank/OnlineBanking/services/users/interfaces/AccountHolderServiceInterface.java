package PaloosaBank.OnlineBanking.services.users.interfaces;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;

import java.util.List;

public interface AccountHolderServiceInterface {

    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);
    List<AccountHolder> getAllAccountHolders();
    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

}
