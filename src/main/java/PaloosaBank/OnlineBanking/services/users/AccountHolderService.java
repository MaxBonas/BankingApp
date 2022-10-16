package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;

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
        return accountHolderRepository.findAll();
    }

    @Override
    public AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder) {
        return null;
    }
}
