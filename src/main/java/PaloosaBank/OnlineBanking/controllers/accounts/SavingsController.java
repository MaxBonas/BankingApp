package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.SavingsControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingsController implements SavingsControllerInterface {

    @Autowired
    SavingsServiceInterface savingsServiceInterface;
}
