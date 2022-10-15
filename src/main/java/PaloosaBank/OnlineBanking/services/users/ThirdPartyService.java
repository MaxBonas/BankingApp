package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.ThirdPartyRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

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
