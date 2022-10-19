package PaloosaBank.OnlineBanking.repositoriesTest.users;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository  extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByName(String name);
    Optional<AccountHolder> findByDateOfBirth(String dateOfBirth);
    Optional<AccountHolder> findByMonthlySpended(String monthlySpended);
    Optional<AccountHolder> findByPrimaryAddress(String primaryAddress);
    Optional<AccountHolder> findByMailingAddress(String mailingAddress);
//    List<AccountHolder> findByPrimaryAccountList(String primaryAccountList);


}
