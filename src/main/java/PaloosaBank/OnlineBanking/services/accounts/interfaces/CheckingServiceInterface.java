package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;

import java.util.List;

public interface CheckingServiceInterface {

    Checking getCheckingById(Long id);
    List<Checking> getAllCheckings();
    List<Checking> findByBalance(Money balance); // Just for testing
}
