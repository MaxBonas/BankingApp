package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.CheckingControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.CheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckingController implements CheckingControllerInterface {

    @Autowired
    CheckingServiceInterface checkingServiceInterface;

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
    public Checking updateChecking(Long id, Checking checking) {
        return null;
    }
}
