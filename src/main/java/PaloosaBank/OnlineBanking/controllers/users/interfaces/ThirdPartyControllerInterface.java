package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;

import java.math.BigDecimal;
import java.util.List;

public interface ThirdPartyControllerInterface {

    Account patchThirdPartyAnyAccountBalance(Long accountId, BigDecimal balance, String hashkey);  // TODO esta bien?
}
