package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;

import java.util.List;

public interface StudentsCheckingServiceInterface {

    StudentsChecking addStudentsChecking(StudentsChecking studentsChecking);
    StudentsChecking getStudentsCheckingById(Long id);
    List<StudentsChecking> getAllStudentsCheckings();
    StudentsChecking updateStudentsChecking(Long id, StudentsChecking studentsChecking);
}
