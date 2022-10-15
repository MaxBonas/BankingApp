package PaloosaBank.OnlineBanking.services.accounts;

import PaloosaBank.OnlineBanking.repositories.accounts.AccountRepository;
import PaloosaBank.OnlineBanking.repositories.accounts.StudentsCheckingRepository;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.StudentsCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsCheckingService implements StudentsCheckingServiceInterface {

    @Autowired
    StudentsCheckingRepository studentsCheckingRepository;
}
