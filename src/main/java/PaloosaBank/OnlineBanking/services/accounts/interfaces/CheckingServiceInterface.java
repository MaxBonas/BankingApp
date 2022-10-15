package PaloosaBank.OnlineBanking.services.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Checking;

import java.util.List;

public interface CheckingServiceInterface {

    Checking addChecking(Checking checking);
    Checking getCheckingById(Long id);
    List<Checking> getAllCheckings();
    Checking updateChecking(Long id, Checking checking);
}
