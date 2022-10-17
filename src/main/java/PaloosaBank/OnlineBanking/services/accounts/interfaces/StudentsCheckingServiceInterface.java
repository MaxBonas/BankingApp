package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;

import java.util.List;

public interface StudentsCheckingServiceInterface {

    StudentsChecking addStudentsChecking(AccountDTO studentsChecking);
    StudentsChecking getStudentsCheckingById(Long id);
    List<StudentsChecking> getAllStudentsCheckings();
    StudentsChecking updateStudentsChecking(Long id, AccountDTO studentsChecking);
}
