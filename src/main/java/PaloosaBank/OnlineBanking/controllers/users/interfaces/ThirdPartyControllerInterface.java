package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;

import java.math.BigDecimal;
import java.util.List;

public interface ThirdPartyControllerInterface {

    ThirdParty addThirdParty(ThirdParty thirdParty);
    ThirdParty getThirdPartyById(Long id);
    List<ThirdParty> getAllThirdPartys();
    ThirdParty updateThirdParty(Long id, ThirdParty thirdParty);
    Account patchThirdPartyAnyAccountBalance(Long accountId, BigDecimal balance, String hashkey);  // TODO esta bien?
}
