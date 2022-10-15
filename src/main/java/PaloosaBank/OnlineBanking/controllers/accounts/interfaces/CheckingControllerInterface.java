package PaloosaBank.OnlineBanking.controllers.accounts.interfaces;

import PaloosaBank.OnlineBanking.entities.accounts.Checking;

import java.util.List;

public interface CheckingControllerInterface {

    Checking addChecking(Checking checking);
    Checking getCheckingById(Long id);
    List<Checking> getAllCheckings();
    Checking updateChecking(Long id, Checking checking);
}
