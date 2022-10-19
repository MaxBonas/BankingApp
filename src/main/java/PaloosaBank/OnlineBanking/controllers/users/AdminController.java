package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.controllers.users.interfaces.AdminControllerInterface;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.entities.users.User;
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
    CreditCardServiceInterface creditCardServiceInterface; //TODO Es necesario aqui para que admin controle?

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
    @PostMapping("/admin")
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
    @GetMapping("/admins")
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
//    ThirdParty getThirdPartyById(Long id);
//    List<ThirdParty> adminGetAllThirdPartys();
//    ThirdParty updateThirdParty();

    @Override
    @PostMapping("/admin/third_party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(@RequestBody ThirdParty thirdParty) {
        return thirdPartyServiceInterface.addThirdParty(thirdParty);
    }

    @Override
    @PostMapping("/admin/account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder (@RequestBody AccountHolder accountHolder) {
        return accountHolderServiceInterface.addAccountHolder(accountHolder);
    }

    @Override
    @PostMapping("/admin/checking_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addChecking(AccountPostDTO checking) {
        return checkingServiceInterface.addChecking(checking);
    }

    @Override
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminServiceInterface.addAdmin(admin);
    }

    @PostMapping("/admin/credit_card")
    @ResponseStatus(HttpStatus.CREATED)
    //TODO Se tendria que hacer uno de cada accountType o solo uno de account y luego especificar?
    public CreditCard addCreditCard(@RequestBody AccountPostDTO creditCard) {
        return creditCardServiceInterface.addCreditCard(creditCard);
    }

    @Override
    @GetMapping("/admin/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account adminGetAccountById(Long id) {
        return accountServiceInterface.getAccountById(id);
    }

    @Override
    @GetMapping("/admin/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> adminGetAllAccounts() {
        return accountServiceInterface.getAllAccounts();
    }

    @Override
    @PatchMapping("/admin/reduce_balance_account")  // todo iria aqui o solo en account?
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account patchAdminAnyAccountBalance(@RequestParam Long accountId, @RequestParam BigDecimal balance) {
        Money balance1 = new Money(balance);
        return accountServiceInterface.patchAdminAnyAccountBalance(accountId, balance1);
    }

    @GetMapping("/credit_cards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getAllCreditCards() {
        return creditCardServiceInterface.getAllCreditCards();
    }

    @GetMapping("/credit_card/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardById(@PathVariable Long id) {
        return creditCardServiceInterface.getCreditCardById(id);
    }



//
//    ThirdParty addThirdParty();
//    ThirdParty getThirdPartyById(Long id);
//    List<ThirdParty> adminGetAllThirdPartys();
//    ThirdParty updateThirdParty();
//    AccountHolder addAccountHolder();
//    ThirdParty getThirdPartyById(Long id);
//    List<AccountHolder> adminGetAllAccountHolders();
//    AccountHolder updateAccountHolder();
//    User deleteUser();
//
//    Account addAccount();
//    Account adminGetAccountById(Long id);
//    List<Account> adminGetAllAccounts();
//    Account updateAccount();
//    Account deleteAccount();
//    Account patchAdminAnyAccountBalance(Long accountId, BigDecimal balance);
//Account patchStatusAccount (Long id);

    //    createAccount()
//    showAccounts()
//    validateAccount()
//    modifyAccount()
//    deleteAccount()
//    freezeAccount()
//    checkBalance()
//    modifyBalance()
//    addThirdParty()
}
