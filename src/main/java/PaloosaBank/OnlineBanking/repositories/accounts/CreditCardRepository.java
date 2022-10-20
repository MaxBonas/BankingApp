package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Checking;
import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findBySecretKey(String secretKey);
//    List<CreditCard> findByBalance(Money balance); // Just for Tests
//    List<CreditCard> findByPrimaryOwner(String primaryOwner);
//    List<CreditCard> findBySecondaryOwner(String secondaryOwner);
//    List<CreditCard> findByStatus(String status);
//    List<CreditCard> findByCreationDate(String creationDate);
}
