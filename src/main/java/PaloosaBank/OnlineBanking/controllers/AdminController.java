package PaloosaBank.OnlineBanking.controllers;

import PaloosaBank.OnlineBanking.DTOs.AccountGetDTO;
import PaloosaBank.OnlineBanking.DTOs.AccountPostDTO;
import PaloosaBank.OnlineBanking.controllers.interfaces.AdminControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.*;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.enums.TypeAccount;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.*;
import PaloosaBank.OnlineBanking.services.users.interfaces.AccountHolderServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.AdminServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.ThirdPartyServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AdminController implements AdminControllerInterface {

    @Autowired
    AdminServiceInterface adminServiceInterface;
    @Autowired
    CreditCardServiceInterface creditCardServiceInterface;
    @Autowired
    AccountServiceInterface accountServiceInterface;
    @Autowired
    CheckingServiceInterface checkingServiceInterface;
    @Autowired
    StudentsCheckingServiceInterface studentsCheckingServiceInterface;
    @Autowired
    SavingsServiceInterface savingsServiceInterface;
    @Autowired
    ThirdPartyServiceInterface thirdPartyServiceInterface;
    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;
    @Autowired
    UserServiceInterface userServiceInterface;


    @Override
    @PostMapping("/admin/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminServiceInterface.addAdmin(admin);
    }

    @Override
    @GetMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin getAdminById(@PathVariable Long id) {
        return adminServiceInterface.getAdminById(id);
    }

    @Override
    @GetMapping("/admin/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAllAdmins() {
        return adminServiceInterface.getAllAdmins();
    }

    @Override
    @PutMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return adminServiceInterface.updateAdmin(id, admin);
    }

    @Override
    @PostMapping("/admin/third_party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(@RequestBody ThirdParty thirdParty) {
        return thirdPartyServiceInterface.addThirdParty(thirdParty);
    }

    @Override
    @GetMapping("/admin/third_party/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty getThirdPartyById(@PathVariable Long id) {
        return thirdPartyServiceInterface.getThirdPartyById(id);
    }

    @Override
    @GetMapping("/admin/third_partys")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAllThirdPartys() {
        return thirdPartyServiceInterface.getAllThirdPartys();
    }

    @Override
    @PutMapping("/admin/third_party/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ThirdParty updateThirdParty(@PathVariable Long id, @RequestBody ThirdParty thirdParty) {
        return thirdPartyServiceInterface.updateThirdParty(id, thirdParty);
    }

    @Override
    @PostMapping("/admin/account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder (@RequestBody AccountHolder accountHolder) {
        return accountHolderServiceInterface.addAccountHolder(accountHolder);
    }


    @Override
    @GetMapping("/admin/account_holder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById(@PathVariable Long id) {
        return accountHolderServiceInterface.getAccountHolderById(id);
    }

    @Override
    @GetMapping("/admin/account_holders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderServiceInterface.getAllAccountHolders();
    }

    @Override
    @PutMapping("/admin/account_holder/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolder updateAccountHolder(@PathVariable Long id, @RequestBody AccountHolder accountHolder) {
        return accountHolderServiceInterface.updateAccountHolder(id,accountHolder);
    }

    @Override
    @DeleteMapping("/admin/user/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String deleteUserById(@PathVariable Long id) {
        return userServiceInterface.deleteUserById(id);
    }


    @Override
    @PostMapping("/admin/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPostDTO addAccountByAdmin(@RequestParam TypeAccount typeAccount, @RequestBody AccountPostDTO account) {
        return accountServiceInterface.addAccountByAdmin(typeAccount, account);
    }

    @Override
    @GetMapping("/admin/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable Long id) {
        return accountServiceInterface.getAccountById(id);
    }


    @Override
    @GetMapping("/admin/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountServiceInterface.getAllAccounts();
    }

    @Override
    @PutMapping("/admin/account/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountGetDTO updateAccountByAdmin(@PathVariable Long id, @RequestBody AccountPostDTO account) {
        return accountServiceInterface.updateAccountByAdmin(id, account);
    }

    @Override
    @DeleteMapping("/admin/account/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteAccountById(@PathVariable Long id) {
        return accountServiceInterface.deleteAccount(id);
    }

    @Override
    @PatchMapping("/admin/reduce_balance_account/{accountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account patchAdminAnyAccountBalance(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        Money amount1 = new Money(amount);
        return accountServiceInterface.patchAdminAnyAccountBalance(accountId, amount1);
    }

    @Override
    @PatchMapping("/admin/change_status_account/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountGetDTO patchStatusAccount(@PathVariable Long id) {
        return accountServiceInterface.patchStatusAccount(id);
    }

    @Override
    @PatchMapping("/admin/validate_account/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountGetDTO validateAndActivateAccount(@PathVariable Long id) {
        return accountServiceInterface.validateAndActivateAccount(id);
    }
}
