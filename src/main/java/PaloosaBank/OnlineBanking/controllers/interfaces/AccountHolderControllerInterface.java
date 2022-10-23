package PaloosaBank.OnlineBanking.controllers.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;
import PaloosaBank.OnlineBanking.enums.TypeAccount;

import java.math.BigDecimal;

public interface AccountHolderControllerInterface {

    String addAccountByHolder(TypeAccount typeAccount, AccountPostDTO account);
    BigDecimal getBalanceAccountAccountHolder(Long id, String secretKey);
    TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO);
}
