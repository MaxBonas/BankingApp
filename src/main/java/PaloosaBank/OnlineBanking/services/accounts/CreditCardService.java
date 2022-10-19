package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.CreditCardRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
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
//        if (creditCard.getInterestRate() > 0.2)
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
//                    "The Credit Card Interest Rate can't be higher than 2%");
//        if (creditCard.getInterestRate() < 0.1)
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
//                    "The Credit Card Interest Rate can't be lower than 1%");

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

        return creditCardRepository.save(new CreditCard(balance, accountHolder, accountHolder2));
    }
}
