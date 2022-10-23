package PaloosaBank.OnlineBanking.controllers.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountGetDTO;
import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.*;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.enums.TypeAccount;

import java.math.BigDecimal;
import java.util.List;

public interface AdminControllerInterface {

    Admin addAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin admin);

    ThirdParty addThirdParty(ThirdParty thirdParty);
    ThirdParty getThirdPartyById(Long id);
    List<ThirdParty> getAllThirdPartys();
    ThirdParty updateThirdParty(Long id, ThirdParty thirdParty);
//    Admin updateAdmin(Long id, Admin admin);
//    ThirdParty updateThirdParty(Long id, ThirdParty thirdParty);
//    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);
    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);
    List<AccountHolder> getAllAccountHolders();
    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);
    String deleteUserById(Long id);


    AccountPostDTO addAccountByAdmin(TypeAccount typeAccount, AccountPostDTO account);
    Account getAccountById(Long id);

    List<Account> getAllAccounts();

    String deleteAccountById(Long id);

    Account patchAdminAnyAccountBalance(Long accountId, BigDecimal amount);
    AccountGetDTO patchStatusAccount (Long id);
//    AccountGetDTO validateAndActivateAccount(Long id);

}
