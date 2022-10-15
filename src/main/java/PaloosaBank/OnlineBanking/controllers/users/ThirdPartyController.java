package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.users.interfaces.ThirdPartyControllerInterface;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;

    @Override
    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        return null;
    }

    @Override
    public ThirdParty getThirdPartyById(Long id) {
        return null;
    }

    @Override
    public List<ThirdParty> getAllThirdPartys() {
        return null;
    }

    @Override
    public ThirdParty updateThirdParty(Long id) {
        return null;
    }
}
