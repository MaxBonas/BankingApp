package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.TransferDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceInterface {

    Account addAccount(AccountPostDTO account);
    Account updateAccount(Long id, AccountPostDTO account);
    Account deleteAccount(Long id);
    Account patchStatusAccount (Long id);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();

    Account patchThirdPartyAnyAccountBalance(Long id, Money balance, String hashkey);
    Account patchAdminAnyAccountBalance(Long id, Money balance);
    TransferDTO transferAccountHolderAnyAccount(Long accountOutId, Long accountInId, Money balance, String secretKey);

    BigDecimal getBalanceAccountAccountHolder(Long id);

}
