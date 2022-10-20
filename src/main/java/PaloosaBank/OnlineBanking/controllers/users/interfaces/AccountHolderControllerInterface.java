package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Account;

import java.math.BigDecimal;

public interface AccountHolderControllerInterface {

//    AccountHolder addAccountHolder(AccountHolder accountHolder);
//    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    BigDecimal getBalanceAccountAccountHolder(Long id);
    TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO);
}
