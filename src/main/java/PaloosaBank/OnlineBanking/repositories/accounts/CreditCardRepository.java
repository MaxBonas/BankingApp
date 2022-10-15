package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository  extends JpaRepository<Account, Long> {

}
