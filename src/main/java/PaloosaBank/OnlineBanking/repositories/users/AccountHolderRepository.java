package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepository  extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByName(String name);
    List<AccountHolder> findByDateOfBirth(LocalDate dateOfBirth);


}
