package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.repositories.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.StudentsCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<StudentsChecking> findByBalance(Money balance) {
        return studentsCheckingRepository.findByBalance(balance);
    }
    @Override
    public List<StudentsChecking> getAllStudentsCheckings() {
        return studentsCheckingRepository.findAll();
    }
}

