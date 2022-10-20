package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.PaymentTPGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceInterface {

    Account addAccount(AccountPostDTO account);
    Account deleteAccount(Long id);
    Account patchStatusAccount (Long id);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();

    PaymentTPGetDTO patchThirdPartyAnyAccountBalance(Long id, Money amount, String hashkey);
    Account patchAdminAnyAccountBalance(Long id, Money amount);
    TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO);

    BigDecimal getBalanceAccountAccountHolder(Long id);

}
