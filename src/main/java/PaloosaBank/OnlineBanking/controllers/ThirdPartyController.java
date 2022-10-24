package PaloosaBank.OnlineBanking.controllers;

import PaloosaBank.OnlineBanking.DTOs.PaymentTPGetDTO;
import PaloosaBank.OnlineBanking.controllers.interfaces.ThirdPartyControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    AccountServiceInterface accountServiceInterface;

    @Override
    @PatchMapping("/third_party/reduce_balance_account/{accountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentTPGetDTO patchThirdPartyAnyAccountBalance(@PathVariable Long accountId, @RequestParam BigDecimal amount,
                                                            @RequestHeader String hashkey) {
        Money amount1 = new Money(amount);
        return accountServiceInterface.patchThirdPartyAnyAccountBalance(accountId, amount1, hashkey);
    }
}
