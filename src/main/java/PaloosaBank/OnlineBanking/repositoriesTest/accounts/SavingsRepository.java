package PaloosaBank.OnlineBanking.repositoriesTest.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsRepository  extends JpaRepository<Savings, Long> {

    Optional<Savings> findBySecretKey(String secretKey);
    Optional<Savings> findByBalance(String balance);
    Optional<Savings> findByPrimaryOwner(String primaryOwner);
    Optional<Savings> findBySecondaryOwner(String secondaryOwner);
    Optional<Savings> findByStatus(String status);
    Optional<Savings> findByCreationDate(String creationDate);
}
