package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.repositories.accounts.CheckingRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CheckingService implements CheckingServiceInterface {

    @Autowired
    CheckingRepository checkingRepository;

    @Override
    public Checking getCheckingById(Long id) {
        return checkingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Checking Account with the given id doesn't exist"));
    }

    public List<Checking> findByBalance(Money balance) {
        return checkingRepository.findByBalance(balance);
    }

    @Override
    public List<Checking> getAllCheckings() {
        return checkingRepository.findAll();
    }
}
