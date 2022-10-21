package PaloosaBank.OnlineBanking.controllers;

import PaloosaBank.OnlineBanking.DTOs.accounts.PaymentTPGetDTO;
import PaloosaBank.OnlineBanking.controllers.interfaces.ThirdPartyControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;

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

//    @Override
//    @PutMapping("/third_party/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public ThirdParty updateThirdParty(@PathVariable Long id, @RequestBody ThirdParty thirdParty) {
//        return thirdPartyServiceInterface.updateThirdParty(id, thirdParty);
//    }
}