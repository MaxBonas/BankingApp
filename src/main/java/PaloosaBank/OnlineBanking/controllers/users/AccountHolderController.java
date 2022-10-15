package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.AccountControllerInterface;
import PaloosaBank.OnlineBanking.controllers.users.interfaces.AccoountHolderControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderController implements AccoountHolderControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;
}