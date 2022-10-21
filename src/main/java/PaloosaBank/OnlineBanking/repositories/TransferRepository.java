package PaloosaBank.OnlineBanking.repositories;

import PaloosaBank.OnlineBanking.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findBySenderAccountId (Long id);
    List<Transfer> findByPrimaryOwnerId (Long id);
}
