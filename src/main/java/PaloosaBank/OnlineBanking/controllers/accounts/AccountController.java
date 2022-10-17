package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.AccountControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController implements AccountControllerInterface {

    @Autowired
    AccountServiceInterface accountServiceInterface;

    @Override
    public Account getAccountById(Long id) {
        return null;
    }

    @Override
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountServiceInterface.getAllAccounts();
    }

}
