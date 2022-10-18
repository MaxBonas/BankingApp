package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.users.AdminRepository;
import PaloosaBank.OnlineBanking.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        if (adminRepository.findById(admin.getId()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "An Admin with this id already exist.");
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Admin with the given id doesn't exist"));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        if (adminRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Admin doesn't exist");

        return adminRepository.save(admin);
    }

    @Override
    public Account patchAdminAnyAccountBalance(Long id, Money balance) {
        Account account1 = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
//        //Todo. hace falta tener en cuenta si el pago es en otra currency? con un if?
        if (account1.getBalance().getAmount().compareTo(balance.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This Account don't have enough founds.");
        }

        account1.setBalance(new Money(account1.getBalance().decreaseAmount(balance.getAmount())));
        return accountRepository.save(account1);
    }
}
