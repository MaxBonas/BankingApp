package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepository  extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByName(String name);
    Optional<User> findByEmail(String email);
    List<AccountHolder> findByDateOfBirth(LocalDate dateOfBirth);
//    List<AccountHolder> findByPrimaryAddress(String primaryAddress);
//    List<AccountHolder> findByMailingAddress(String mailingAddress);
//    List<AccountHolder> findByPrimaryAccountList(String primaryAccountList);


}
