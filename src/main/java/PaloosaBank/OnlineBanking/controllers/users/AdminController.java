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

//    @Autowired
//    CreditCardServiceInterface creditCardServiceInterface; //TODO Es necesario aqui para que admin controle?

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

//    createAccount()
//    showAccounts()
//    validateAccount()
//    modifyAccount()
//    deleteAccount()
//    freezeAccount()
//    checkBalance()
//    modifyBalance()
//    addThirdParty()

//    @PostMapping("/credit_card")
//    @ResponseStatus(HttpStatus.CREATED)
//    //TODO Se tendria que hacer uno de cada accountType o solo uno de account y luego especificar?
//    public CreditCard addCreditCard(@RequestBody CreditCard creditCard) {
//        return creditCardServiceInterface.addCreditCard(creditCard);
//    }


}
