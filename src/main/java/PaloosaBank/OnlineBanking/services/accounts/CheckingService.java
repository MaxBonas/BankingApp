package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckingService implements CheckingServiceInterface {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public Checking addChecking(AccountDTO checking) {
        AccountHolder accountHolder = accountHolderRepository.findById(checking.getPrimaryOwnerId()).get();
        AccountHolder accountHolder2 = null;
        if (checking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(checking.getSecondaryOwnerId()).get();
        }
        Money balance = new Money(BigDecimal.valueOf(checking.getBalance()));

        return checkingRepository.save(new Checking(balance, accountHolder, accountHolder2));
    }

    @Override
    public Checking getCheckingById(Long id) {
        return checkingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Checking Account with the given id doesn't exist"));
    }

    @Override
    public List<Checking> getAllCheckings() {
        return checkingRepository.findAll();
    }

    @Override
    public Checking updateChecking(Long id, AccountDTO checking) {
        if (checkingRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Checking Account doesn't exist");

//        if (checking.getPrimaryOwnerId() == null) //todo Esto con el @Notnull quiza no hace falta?
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You must to insert at least a Primary Owner.");

        AccountHolder accountHolder = accountHolderRepository.findById(checking.getPrimaryOwnerId()).get();
        AccountHolder accountHolder2 = null;

        if (checking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(checking.getSecondaryOwnerId()).get();
        }

        Money balance = new Money(BigDecimal.valueOf(checking.getBalance()));

        return checkingRepository.save(new Checking(balance, accountHolder, accountHolder2));
    }
}
