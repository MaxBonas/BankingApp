package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;

import java.util.List;

public interface StudentsCheckingControllerInterface {

    StudentsChecking updateStudentsChecking(Long id, AccountPostDTO studentsChecking);
}
