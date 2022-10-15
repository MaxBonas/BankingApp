package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.StudentsCheckingControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.StudentsCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsCheckingController implements StudentsCheckingControllerInterface {

    @Autowired
    StudentsCheckingServiceInterface studentsCheckingServiceInterface;

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
        return null;
    }

    @Override
    public StudentsChecking updateStudentsChecking(Long id, StudentsChecking studentsChecking) {
        return null;
    }
}
