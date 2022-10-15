package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.users.interfaces.AccountHolderControllerInterface;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return null;
    }

    @Override
    public AccountHolder getAccountHolderById(Long id) {
        return null;
    }

    @Override
    public List<AccountHolder> getAllAccountHolders() {
        return null;
    }

    @Override
    public AccountHolder updateAccountHolder(Long id) {
        return null;
    }
}
