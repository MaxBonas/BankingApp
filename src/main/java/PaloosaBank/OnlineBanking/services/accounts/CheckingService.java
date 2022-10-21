package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
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
    public Account addChecking(AccountPostDTO checking) {
        Money balance = new Money(BigDecimal.valueOf(checking.getBalance()));
        AccountHolder accountHolder = accountHolderRepository.findById(checking.getPrimaryOwnerId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "An Account Holder with the given id doesn't exist"));
        AccountHolder accountHolder2 = null;
        if (checking.getSecondaryOwnerId() != null) {
            accountHolder2 = accountHolderRepository.findById(checking.getSecondaryOwnerId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "An Account Holder with the given id doesn't exist"));
        }
        LocalDate birth1 = accountHolder.getDateOfBirth();
        Period period = Period.between(birth1, LocalDate.now());
        if (period.getYears() < 24) {
            return studentsCheckingRepository.save(new StudentsChecking(balance, accountHolder, accountHolder2));
        }

        Checking checking1 = new Checking(balance, accountHolder, accountHolder2);
        checking1.setMinimumBalance(new Money(BigDecimal.valueOf(checking.getMinimumBalance())));
        checking1.setMonthlyMaintenanceFee(new Money(BigDecimal.valueOf(checking.getMonthlyFee())));
        return checkingRepository.save(checking1);
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
        Checking checking1 = new Checking(balance, accountHolder, accountHolder2);
        checking1.setId(id);
        checking1.setMinimumBalance(new Money(BigDecimal.valueOf(checking.getMinimumBalance())));
        checking1.setMonthlyMaintenanceFee(new Money(BigDecimal.valueOf(checking.getMonthlyFee())));
        return checkingRepository.save(checking1);
    }
}
