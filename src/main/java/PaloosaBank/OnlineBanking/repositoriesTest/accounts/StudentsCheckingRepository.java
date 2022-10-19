package PaloosaBank.OnlineBanking.repositoriesTest.accounts;

import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsCheckingRepository  extends JpaRepository<StudentsChecking, Long> {

    Optional<StudentsChecking> findBySecretKey(String secretKey);
    Optional<StudentsChecking> findByBalance(String balance);
    Optional<StudentsChecking> findByPrimaryOwner(String primaryOwner);
    Optional<StudentsChecking> findBySecondaryOwner(String secondaryOwner);
    Optional<StudentsChecking> findByStatus(String status);
    Optional<StudentsChecking> findByCreationDate(String creationDate);
}
