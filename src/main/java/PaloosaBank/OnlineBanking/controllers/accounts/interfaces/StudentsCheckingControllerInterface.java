package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;

import java.util.List;

public interface StudentsCheckingControllerInterface {

    StudentsChecking addStudentsChecking(StudentsChecking studentsChecking);
    StudentsChecking getStudentsCheckingById(Long id);
    List<StudentsChecking> getAllStudentsCheckings();
    StudentsChecking updateStudentsChecking(Long id);
}
