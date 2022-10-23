package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;

import java.util.List;

public interface CheckingServiceInterface {

    Checking getCheckingById(Long id);
    List<Checking> getAllCheckings();
    Checking updateChecking(Long id, AccountPostDTO checking);
}
