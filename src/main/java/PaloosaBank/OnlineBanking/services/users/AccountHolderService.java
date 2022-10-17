package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        if (accountHolderRepository.findById(accountHolder.getId()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "An Account Holder with this id already exist.");
        return accountHolderRepository.save(accountHolder);
    }

    @Override
    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Account Holder with the given id doesn't exist"));
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

//if (accountHolderRepository.findById(accountHolder.getId()).isPresent())
//        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
//        "An Account Holder with this id already exist.");
//        return accountHolderRepository.save(accountHolder);