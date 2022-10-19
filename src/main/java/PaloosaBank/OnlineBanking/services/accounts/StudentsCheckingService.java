package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositoriesTest.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositoriesTest.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.StudentsCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentsCheckingService implements StudentsCheckingServiceInterface {

    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public StudentsChecking addStudentsChecking(AccountPostDTO studentsChecking) {
        AccountHolder accountHolder = accountHolderRepository.findById(studentsChecking.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (studentsChecking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(studentsChecking.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }
        Money balance = new Money(BigDecimal.valueOf(studentsChecking.getBalance()));
        return studentsCheckingRepository.save(new StudentsChecking(balance, accountHolder, accountHolder2));
    }

    @Override
    public StudentsChecking getStudentsCheckingById(Long id) {
        return studentsCheckingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Students Checking Account with the given id doesn't exist"));
    }

    @Override
    public List<StudentsChecking> getAllStudentsCheckings() {
        return studentsCheckingRepository.findAll();
    }

    @Override
    public StudentsChecking updateStudentsChecking(Long id, AccountPostDTO studentsChecking) {
        if (studentsCheckingRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Students Checking Account doesn't exist");

        AccountHolder accountHolder = accountHolderRepository.findById(studentsChecking.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;

        if (studentsChecking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(studentsChecking.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }

        Money balance = new Money(BigDecimal.valueOf(studentsChecking.getBalance()));

        return studentsCheckingRepository.save(new StudentsChecking(balance, accountHolder, accountHolder2));
    }

//    }

//    if (accountHolderRepository.findById(accountHolder.getId()).isPresent())
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
//        "An Account Holder with this id already exist.");
//        return accountHolderRepository.save(accountHolder);

}

