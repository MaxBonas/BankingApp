package PaloosaBank.OnlineBanking.repositoriesTest.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

    Optional<Checking> findBySecretKey(String secretKey);
    Optional<Checking> findByBalance(String balance);
    Optional<Checking> findByPrimaryOwner(String primaryOwner);
    Optional<Checking> findBySecondaryOwner(String secondaryOwner);
    Optional<Checking> findByStatus(String status);
    Optional<Checking> findByCreationDate(String creationDate);
}
