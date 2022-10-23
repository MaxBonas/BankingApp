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
}
