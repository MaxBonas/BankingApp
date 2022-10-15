package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;

import java.util.List;

public interface AccountHolderControllerInterface {

    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);
    List<AccountHolder> getAllAccountHolders();
    AccountHolder updateAccountHolder(Long id);
}
