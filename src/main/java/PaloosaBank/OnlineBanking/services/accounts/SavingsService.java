package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
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
    public Savings updateSavings(Long id, AccountPostDTO savings) {
        if (savingsRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Savings Account doesn't exist");

        AccountHolder accountHolder = accountHolderRepository.findById(savings.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;

        if (savings.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(savings.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }

        Money balance = new Money(BigDecimal.valueOf(savings.getBalance()));
        Savings savings1 = new Savings(balance, accountHolder, accountHolder2);
        savings1.setId(id);
        savings1.setMinimumBalance(new Money(BigDecimal.valueOf(savings.getMinimumBalance())));
        savings1.setInterestRate(savings.getInterestRate());
        return savingsRepository.save(savings1);
    }
}
