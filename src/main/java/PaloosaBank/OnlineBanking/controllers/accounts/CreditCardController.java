package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.CreditCardControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardController implements CreditCardControllerInterface {

    @Autowired
    CreditCardServiceInterface creditCardServiceInterface;

    @Override
    @PostMapping("/admin/credit_card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCard(@RequestBody AccountDTO creditCard) {
        return creditCardServiceInterface.addCreditCard(creditCard);
    }

    @Override
    @GetMapping("/credit_card/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardById(@PathVariable Long id) {
        return creditCardServiceInterface.getCreditCardById(id);
    }

    @Override
    @GetMapping("/credit_cards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getAllCreditCards() {
        return creditCardServiceInterface.getAllCreditCards();
    }

    @Override
    @PutMapping("/admin/credit_card/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard updateCreditCard(@PathVariable Long id, @RequestBody AccountDTO creditCard) {
        return creditCardServiceInterface.updateCreditCard(id, creditCard);
    }
}
