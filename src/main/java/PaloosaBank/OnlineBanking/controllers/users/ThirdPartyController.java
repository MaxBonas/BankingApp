package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.users.interfaces.ThirdPartyControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;
}
