package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.repositories.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SavingsService implements SavingsServiceInterface {

    @Autowired
    SavingsRepository savingsRepository;

    @Override
    public Savings getSavingsById(Long id) {
        return savingsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Savings Account with the given id doesn't exist"));
    }

    public List<Savings> findByBalance(Money balance) {
        return savingsRepository.findByBalance(balance);
    }
    @Override
    public List<Savings> getAllSavings() {
        return savingsRepository.findAll();
    }
}
