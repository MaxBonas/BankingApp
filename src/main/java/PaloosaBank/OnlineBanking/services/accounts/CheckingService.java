package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
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

    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;

    @Override
    public Checking addChecking(AccountPostDTO checking) {
        AccountHolder accountHolder = accountHolderRepository.findById(checking.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (checking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(checking.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }
//        if (accountHolder.getDateOfBirth().)  // class.period. Resviso luego
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
    public Checking updateChecking(Long id, AccountPostDTO checking) {
        if (checkingRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Checking Account doesn't exist");

//        if (checking.getPrimaryOwnerId() == null) //todo Esto con el @Notnull quiza no hace falta?
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You must to insert at least a Primary Owner.");

        AccountHolder accountHolder = accountHolderRepository.findById(checking.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;

        if (checking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(checking.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }

        Money balance = new Money(BigDecimal.valueOf(checking.getBalance()));

        return checkingRepository.save(new Checking(balance, accountHolder, accountHolder2));
    }
}
