package PaloosaBank.OnlineBanking.repositoriesTest.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findBySecretKey(String secretKey);
    Optional<CreditCard> findByBalance(String balance);
    Optional<CreditCard> findByPrimaryOwner(String primaryOwner);
    Optional<CreditCard> findBySecondaryOwner(String secondaryOwner);
    Optional<CreditCard> findByStatus(String status);
    Optional<CreditCard> findByCreationDate(String creationDate);
}
