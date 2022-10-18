package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.Admin;

import java.math.BigDecimal;
import java.util.List;

public interface AdminControllerInterface {

    Admin addAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin admin);

    Account adminGetAccountById(Long id);
    List<Account> adminGetAllAccounts();
    Account patchAdminAnyAccountBalance(Long accountId, BigDecimal balance);
}
