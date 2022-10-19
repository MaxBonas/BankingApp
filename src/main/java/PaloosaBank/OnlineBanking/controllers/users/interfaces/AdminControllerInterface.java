package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.*;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.entities.users.User;

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
    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(Long id);
    List<AccountHolder> getAllAccountHolders();
    AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder);
    String deleteUserById(Long id);

    Checking addChecking(AccountPostDTO checking);
    CreditCard addCreditCard(AccountPostDTO creditCard);
    Savings addSavings(AccountPostDTO savings);
    StudentsChecking addStudentsChecking(AccountPostDTO studentsChecking);

//    Account addAccount(AccountPostDTO account); // todo esto que?
    Account getAccountById(Long id);

    List<Account> getAllAccounts();
    Account updateAccount(Long id, AccountPostDTO account);

    String deleteAccountById(Long id);

    Account patchAdminAnyAccountBalance(Long accountId, BigDecimal balance);
    Account patchStatusAccount (Long id);

}
