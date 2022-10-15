package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingsService implements SavingsServiceInterface {

    @Autowired
    SavingsRepository savingsRepository;

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
