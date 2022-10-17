package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public CreditCard addCreditCard(AccountDTO creditCard) {
        AccountHolder accountHolder = accountHolderRepository.findById(creditCard.getPrimaryOwnerId()).get();
        AccountHolder accountHolder2 = null;
        if (creditCard.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(creditCard.getSecondaryOwnerId()).get();
        }
        Money balance = new Money(BigDecimal.valueOf(creditCard.getBalance()));
        return creditCardRepository.save(new CreditCard(balance, accountHolder, accountHolder2));
    }

    @Override
    public CreditCard getCreditCardById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Credit Card with the given id doesn't exist"));
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard updateCreditCard(Long id, AccountDTO creditCard) {
        if (creditCardRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Credit Card Account doesn't exist");

        AccountHolder accountHolder = accountHolderRepository.findById(creditCard.getPrimaryOwnerId()).get();
        AccountHolder accountHolder2 = null;

        if (creditCard.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(creditCard.getSecondaryOwnerId()).get();
        }

        Money balance = new Money(BigDecimal.valueOf(creditCard.getBalance()));

        return creditCardRepository.save(new CreditCard(balance, accountHolder, accountHolder2));
    }
}
