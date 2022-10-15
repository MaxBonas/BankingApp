package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.SavingsControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavingsController implements SavingsControllerInterface {

    @Autowired
    SavingsServiceInterface savingsServiceInterface;

    @Override
    public Savings addSavings(Savings savings) {
        return null;
    }

    @Override
    public Savings getSavingsById(Long id) {
        return null;
    }

    @Override
    public List<Savings> getAllSavings() {
        return null;
    }

    @Override
    public Savings updateSavings(Long id, Savings savings) {
        return null;
    }
}
