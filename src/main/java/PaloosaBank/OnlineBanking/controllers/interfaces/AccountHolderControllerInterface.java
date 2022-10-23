package PaloosaBank.OnlineBanking.controllers.interfaces;

import PaloosaBank.OnlineBanking.DTOs.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.TransferPostDTO;
import PaloosaBank.OnlineBanking.enums.TypeAccount;

import java.math.BigDecimal;

public interface AccountHolderControllerInterface {

    AccountPostDTO addAccountByHolder(TypeAccount typeAccount, AccountPostDTO account);
    BigDecimal getBalanceAccountAccountHolder(Long id, String secretKey);
    TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO);
}
