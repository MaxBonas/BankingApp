package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.TransferDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderControllerInterface {

//    AccountHolder addAccountHolder(AccountHolder accountHolder);
//    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    TransferDTO transferAccountHolderAnyAccount(Long accountOutId, Long accountInId, BigDecimal balance, String secretKey);
}
