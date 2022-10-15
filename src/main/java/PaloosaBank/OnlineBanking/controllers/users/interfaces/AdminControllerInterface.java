package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.Admin;

import java.util.List;

public interface AdminControllerInterface {

    Admin addAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id);
}
