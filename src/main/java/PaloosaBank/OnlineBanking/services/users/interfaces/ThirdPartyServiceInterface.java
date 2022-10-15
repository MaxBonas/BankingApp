package PaloosaBank.OnlineBanking.services.users.interfaces;

import PaloosaBank.OnlineBanking.entities.users.ThirdParty;

import java.util.List;

public interface ThirdPartyServiceInterface {

    ThirdParty addThirdParty(ThirdParty thirdParty);
    ThirdParty getThirdPartyById(Long id);
    List<ThirdParty> getAllThirdPartys();
    ThirdParty updateThirdParty(Long id);
}
