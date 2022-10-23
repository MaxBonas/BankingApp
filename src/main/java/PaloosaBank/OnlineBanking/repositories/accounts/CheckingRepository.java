package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

    Optional<Checking> findBySecretKey(String secretKey);
    List<Checking> findByBalance(Money balance); // Just for testing
//    List<Checking> findByPrimaryOwner(String primaryOwner);
//    List<Checking> findBySecondaryOwner(String secondaryOwner);
//    List<Checking> findByStatus(String status);
//    List<Checking> findByCreationDate(String creationDate);
}
