package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.StudentsCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsCheckingService implements StudentsCheckingServiceInterface {

    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;

    @Override
    public StudentsChecking addStudentsChecking(StudentsChecking studentsChecking) {
        return null;
    }

    @Override
    public StudentsChecking getStudentsCheckingById(Long id) {
        return null;
    }

    @Override
    public List<StudentsChecking> getAllStudentsCheckings() {
        return studentsCheckingRepository.findAll();
    }

    @Override
    public StudentsChecking updateStudentsChecking(Long id, StudentsChecking studentsChecking) {
        return null;
    }
}
