package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;

import java.util.List;

public interface StudentsCheckingServiceInterface {

    StudentsChecking getStudentsCheckingById(Long id);
    List<StudentsChecking> getAllStudentsCheckings();
    List<StudentsChecking> findByBalance(Money balance); // Just for testing
}
