package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.users.interfaces.ThirdPartyControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;

    @Autowired
    AccountServiceInterface accountServiceInterface; // Todo. solo si va aquí el patchBalance

    @Override
    @PostMapping("/third_party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        return thirdPartyServiceInterface.addThirdParty(thirdParty);
    }

    @Override
    @GetMapping("/third_party/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty getThirdPartyById(@PathVariable Long id) {
        return thirdPartyServiceInterface.getThirdPartyById(id);
    }

    @Override
    @GetMapping("/third_partys")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAllThirdPartys() {
        return thirdPartyServiceInterface.getAllThirdPartys();
    }

    @Override
    @PutMapping("/third_party/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ThirdParty updateThirdParty(@PathVariable Long id, @RequestBody ThirdParty thirdParty) {
        return thirdPartyServiceInterface.updateThirdParty(id, thirdParty);
    }

    @Override
    @PatchMapping("/third_party/reduce_balance_account")  // todo iria aqui o solo en account?
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account patchThirdPartyAnyAccountBalance(@RequestParam Long accountId, @RequestParam BigDecimal balance,
                                                    @RequestHeader String hashkey) {
        Money balance1 = new Money(balance);
        return accountServiceInterface.patchThirdPartyAnyAccountBalance(accountId, balance1, hashkey);
    }
}
