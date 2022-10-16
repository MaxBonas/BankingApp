package PaloosaBank.OnlineBanking.repositories.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsCheckingRepository  extends JpaRepository<StudentsChecking, Long> {

}
