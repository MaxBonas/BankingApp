package PaloosaBank.OnlineBanking.repositoriesTest.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findBySecretKey(String secretKey);
    Optional<Account> findByBalance(String balance);
    Optional<Account> findByPrimaryOwner(String primaryOwner);
    Optional<Account> findBySecondaryOwner(String secondaryOwner);
    Optional<Account> findByStatus(String status);
    Optional<Account> findByCreationDate(String creationDate);

}
