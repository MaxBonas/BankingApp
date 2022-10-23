package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.repositories.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.repositories.users.AccountHolderRepository;
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
}

