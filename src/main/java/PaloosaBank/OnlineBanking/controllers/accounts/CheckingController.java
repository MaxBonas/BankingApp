package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.CheckingControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckingController implements CheckingControllerInterface {

    @Autowired
    CheckingServiceInterface checkingServiceInterface;

    @Override
    @PostMapping("/checking_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking addChecking(AccountDTO checking) {
        return checkingServiceInterface.addChecking(checking);
    }

    @Override
    @GetMapping("/checking_account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingById(Long id) {
        return checkingServiceInterface.getCheckingById(id);
    }

    @Override
    @GetMapping("/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> getAllCheckings() {
        return checkingServiceInterface.getAllCheckings();
    }

    @Override
    @PutMapping("/checking_account/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Checking updateChecking(@PathVariable Long id, @RequestBody AccountDTO checking) {
        return checkingServiceInterface.updateChecking(id, checking);
    }
}
