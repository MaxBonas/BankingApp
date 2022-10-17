package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SavingsService implements SavingsServiceInterface {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public Savings addSavings(AccountDTO savings) {
        AccountHolder accountHolder = accountHolderRepository.findById(savings.getPrimaryOwnerId()).get();
        AccountHolder accountHolder2 = null;
        if (savings.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(savings.getSecondaryOwnerId()).get();
        }
        Money balance = new Money(BigDecimal.valueOf(savings.getBalance()));
        return savingsRepository.save(new Savings(balance, accountHolder, accountHolder2));
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
    public Savings updateSavings(Long id, AccountDTO savings) {
        return null;
    }
}
