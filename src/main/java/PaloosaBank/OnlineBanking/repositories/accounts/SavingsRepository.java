package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingsRepository  extends JpaRepository<Savings, Long> {

    Optional<Savings> findBySecretKey(String secretKey);
    List<Savings> findByBalance(Money balance); // Just for testing
//    List<Savings> findByBalance(Money balance); // Just for Tests
//    List<Savings> findByPrimaryOwner(String primaryOwner);
//    List<Savings> findBySecondaryOwner(String secondaryOwner);
//    List<Savings> findByStatus(String status);
//    List<Savings> findByCreationDate(String creationDate);
}
