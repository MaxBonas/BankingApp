package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingService implements CheckingServiceInterface {

    @Autowired
    CheckingRepository checkingRepository;

    @Override
    public Checking addChecking(Checking checking) {
        return null;
    }

    @Override
    public Checking getCheckingById(Long id) {
        return null;
    }

    @Override
    public List<Checking> getAllCheckings() {
        return null;
    }

    @Override
    public Checking updateChecking(Long id) {
        return null;
    }
}
