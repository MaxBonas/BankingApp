package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
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
    public Savings addSavings(Savings savings) {
        if (savingsRepository.findById(savings.getId()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "A Savings Account with this id already exist.");
        return savingsRepository.save(savings);
    }

    @Override
    public Savings getSavingsById(Long id) {
        return savingsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Savings Account with the given id doesn't exist"));
    }

    @Override
    public List<Savings> getAllSavings() {
        return savingsRepository.findAll();
    }

    @Override
    public Savings updateSavings(Long id, Savings savings) {
        return null;
    }
}
