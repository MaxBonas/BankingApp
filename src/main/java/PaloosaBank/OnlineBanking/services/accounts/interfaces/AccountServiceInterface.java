package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.*;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.enums.TypeAccount;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceInterface {

    AccountPostDTO addAccountByAdmin(TypeAccount typeAccount, AccountPostDTO checking);
    void addAccountByHolder(TypeAccount typeAccount, AccountPostDTO account);
    String deleteAccount(Long id);
    AccountGetDTO patchStatusAccount (Long id);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();

    PaymentTPGetDTO patchThirdPartyAnyAccountBalance(Long id, Money amount, String hashkey);
    Account patchAdminAnyAccountBalance(Long id, Money amount);
    TransferGetDTO transferAccountHolderAnyAccount(TransferPostDTO transferPostDTO);

    BigDecimal getBalanceAccountAccountHolder(Long id);

}
