package PaloosaBank.OnlineBanking.controllers;

import PaloosaBank.OnlineBanking.DTOs.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.TransferPostDTO;
import PaloosaBank.OnlineBanking.controllers.interfaces.AccountHolderControllerInterface;
import PaloosaBank.OnlineBanking.enums.TypeAccount;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {

    @Autowired
    AccountServiceInterface accountServiceInterface;

    @Override
    @PatchMapping("/account_holder/transfer_amount_account")  // todo iria aqui o solo en account?
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransferGetDTO transferAccountHolderAnyAccount(@RequestBody TransferPostDTO transferPostDTO) {
        return accountServiceInterface.transferAccountHolderAnyAccount(transferPostDTO);
    }

    @Override
    @GetMapping("/account_holder/check_balance_account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalanceAccountAccountHolder(@PathVariable Long id, @RequestParam String secretKey) {
        return accountServiceInterface.getBalanceAccountAccountHolder(id, secretKey);
    }

    @Override
    @PostMapping("/account_holder/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPostDTO addAccountByHolder(@RequestParam TypeAccount typeAccount, @RequestBody AccountPostDTO account) {
        System.out.println("Your Account has been solicitated correctly. Please, wait until we process your data, " +
                "and we will activate it and contact with you.");
        return accountServiceInterface.addAccountByHolder(typeAccount, account);
    }
}
