package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account addAccount(Account account) {
        return null;
    }

    @Override
    public Account getAccountById(Long id) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll(); //TODO hace falta hacer estos metodos en las clases abstract?
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return null;
    }
}
