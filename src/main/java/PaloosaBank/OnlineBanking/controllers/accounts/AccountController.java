package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.AccountControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

//    @Override
//    @PatchMapping("/third_party/payments_account/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public Account patchThirdPartyAnyAccountBalance(@PathVariable Long id, @RequestParam Money balance, @RequestHeader String hashkey) {
//
//        return accountServiceInterface.patchThirdPartyAnyAccountBalance(id, balance, hashkey);
//    }

//    @Override
//    @PatchMapping("/admin/balance_account/{id}") // todo . lo repito para admin? o uso el mismo?
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public Account patchBalanceAnyAccount(@PathVariable Long id, @RequestParam Money balance) {
//
//        return accountServiceInterface.patchBalanceAnyAccount(id, balance);
//    }

}
