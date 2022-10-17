package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.SavingsControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavingsController implements SavingsControllerInterface {

    @Autowired
    SavingsServiceInterface savingsServiceInterface;

    @Override
    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavings(AccountDTO savings) {
        return savingsServiceInterface.addSavings(savings);
    }

    @Override
    @GetMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingsById(Long id) {
        return savingsServiceInterface.getSavingsById(id);
    }

    @Override
    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> getAllSavings() {
        return savingsServiceInterface.getAllSavings();
    }

    @Override
    @PutMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Savings updateSavings(@PathVariable Long id, @RequestBody AccountDTO savings) {
        return savingsServiceInterface.updateSavings(id, savings);
    }
}
