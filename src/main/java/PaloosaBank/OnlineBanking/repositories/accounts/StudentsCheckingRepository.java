package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsCheckingRepository  extends JpaRepository<StudentsChecking, Long> {

    Optional<StudentsChecking> findBySecretKey(String secretKey);
    List<StudentsChecking> findByBalance(Money balance); // Just for testing
}
