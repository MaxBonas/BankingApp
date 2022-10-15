package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.SavingsRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.SavingsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsService implements SavingsServiceInterface {

    @Autowired
    SavingsRepository savingsRepository;
}
