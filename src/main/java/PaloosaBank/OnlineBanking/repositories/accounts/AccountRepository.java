package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findBySecretKey(String secretKey);
//    List<Account> findByBalance(Money balance); // Just for tests
//    List<Account> findByPrimaryOwner(String primaryOwner);
//    List<Account> findBySecondaryOwner(String secondaryOwner);
//    List<Account> findByStatus(String status);
//    List<Account> findByCreationDate(String creationDate);

}
