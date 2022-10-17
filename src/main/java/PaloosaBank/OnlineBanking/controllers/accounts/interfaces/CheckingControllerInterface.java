package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;

import java.util.List;

public interface CheckingControllerInterface {

    Checking addChecking(AccountDTO checking);
    Checking getCheckingById(Long id);
    List<Checking> getAllCheckings();
    Checking updateChecking(Long id, AccountDTO checking);
}
