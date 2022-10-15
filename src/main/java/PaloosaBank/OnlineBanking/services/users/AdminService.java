package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.AdminRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        return null;
    }

    @Override
    public Admin getAdminById(Long id) {
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return null;
    }

    @Override
    public Admin updateAdmin(Long id) {
        return null;
    }
}
