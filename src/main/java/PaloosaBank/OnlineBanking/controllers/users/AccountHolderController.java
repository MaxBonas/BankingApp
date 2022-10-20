package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;
import PaloosaBank.OnlineBanking.controllers.users.interfaces.AccountHolderControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;

    @Autowired
    AccountServiceInterface accountServiceInterface;


    @Override
    @GetMapping("/account_holder/check_balance_account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalanceAccountAccountHolder(@PathVariable Long id) {
        return accountServiceInterface.getBalanceAccountAccountHolder(id);
    }

    @Override
    @PatchMapping("/account_holder/transfer_amount_account")  // todo iria aqui o solo en account?
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferGetDTO transferAccountHolderAnyAccount(@RequestBody TransferPostDTO transferPostDTO) {
        return accountServiceInterface.transferAccountHolderAnyAccount(transferPostDTO);
    }


//    @Override
//    @PostMapping("/account_holder")
//    @ResponseStatus(HttpStatus.CREATED)
//    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
//        return accountHolderServiceInterface.addAccountHolder(accountHolder);
//    }
//

//
//    @Override
//    @GetMapping("/account_holders")
//    @ResponseStatus(HttpStatus.OK)
//    public List<AccountHolder> getAllAccountHolders() {
//        return accountHolderServiceInterface.getAllAccountHolders();
//    }
//
//    @Override
//    @PutMapping("/account_holder/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public AccountHolder updateAccountHolder(@PathVariable Long id, @RequestBody AccountHolder accountHolder) {
//        return accountHolderServiceInterface.updateAccountHolder(id,accountHolder);
//    }
}
