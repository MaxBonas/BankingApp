package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.AccountControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController implements AccountControllerInterface {

    @Autowired
    AccountServiceInterface accountServiceInterface;
}
