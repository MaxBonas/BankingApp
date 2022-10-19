package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.TransferDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderControllerInterface {

//    AccountHolder addAccountHolder(AccountHolder accountHolder);
//    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    Account getBalanceAccountAccountHolder(Long id);
    TransferDTO transferAccountHolderAnyAccount(Long accountOutId, Long accountInId, BigDecimal balance, String secretKey);
}
