package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.users.interfaces.AdminControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController implements AdminControllerInterface {

    @Autowired
    AdminServiceInterface adminServiceInterface;

    @Autowired
    CreditCardServiceInterface creditCardServiceInterface; //TODO Es necesario aqui para que admin controle?

    @Override
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestParam String name) {
        Admin admin = new Admin();
        admin.setName(name);
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

//    createAccount()
//    showAccounts()
//    validateAccount()
//    modifyAccount()
//    deleteAccount()
//    freezeAccount()
//    checkBalance()
//    modifyBalance()
//    addThirdParty()

    @PostMapping("/admin/credit_card")
    @ResponseStatus(HttpStatus.CREATED)
    //TODO Se tendria que hacer uno de cada accountType o solo uno de account y luego especificar?
    public CreditCard addCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardServiceInterface.addCreditCard(creditCard);
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




}
