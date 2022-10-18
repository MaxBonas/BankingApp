package PaloosaBank.OnlineBanking.services.users.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.Admin;

import java.util.List;

public interface AdminServiceInterface {

    Admin addAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin admin);

    Account adminGetAccountById(Long id);
    List<Account> adminGetAllAccounts();
    Account patchAdminAnyAccountBalance(Long accountId, Money balance);
}
