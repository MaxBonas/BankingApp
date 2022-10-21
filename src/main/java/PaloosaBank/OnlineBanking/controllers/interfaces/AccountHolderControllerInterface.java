package PaloosaBank.OnlineBanking.controllers.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;

import java.math.BigDecimal;

public interface AccountHolderControllerInterface {

//    AccountHolder addAccountHolder(AccountHolder accountHolder);
//    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);

    BigDecimal getBalanceAccountAccountHolder(Long id);
    TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO);
}
