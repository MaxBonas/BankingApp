package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
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
    public CreditCard addCreditCard(AccountPostDTO creditCard) {
        AccountHolder accountHolder = accountHolderRepository.findById(creditCard.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (creditCard.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(creditCard.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }

        Money balance = new Money(BigDecimal.valueOf(creditCard.getBalance()));
        CreditCard creditCard1 = new CreditCard(balance, accountHolder, accountHolder2);
        creditCard1.setCreditLimit(new Money(BigDecimal.valueOf(creditCard.getCreditLimit())));
        creditCard1.setInterestRate(creditCard.getInterestRate());
        return creditCardRepository.save(creditCard1);
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
    public CreditCard updateCreditCard(Long id, AccountPostDTO creditCard) {
        if (creditCardRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Credit Card Account doesn't exist");

        AccountHolder accountHolder = accountHolderRepository.findById(creditCard.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;

        if (creditCard.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(creditCard.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }

        Money balance = new Money(BigDecimal.valueOf(creditCard.getBalance()));
        CreditCard creditCard1 = new CreditCard(balance, accountHolder, accountHolder2);
        creditCard1.setId(id);
        creditCard1.setCreditLimit(new Money(BigDecimal.valueOf(creditCard.getCreditLimit())));
        creditCard1.setInterestRate(creditCard.getInterestRate());
        return creditCardRepository.save(creditCard1);
    }
}
