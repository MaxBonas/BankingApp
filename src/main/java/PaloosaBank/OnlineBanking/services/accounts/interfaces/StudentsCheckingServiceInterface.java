package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;

import java.util.List;

public interface StudentsCheckingServiceInterface {

    StudentsChecking getStudentsCheckingById(Long id);
    List<StudentsChecking> getAllStudentsCheckings();
}
