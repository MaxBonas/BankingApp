package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.CheckingControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingController implements CheckingControllerInterface {

    @Autowired
    CheckingServiceInterface checkingServiceInterface;
}
