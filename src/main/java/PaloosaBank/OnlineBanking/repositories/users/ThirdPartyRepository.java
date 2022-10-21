package PaloosaBank.OnlineBanking.repositories.users;

import PaloosaBank.OnlineBanking.entities.users.ThirdParty;
import PaloosaBank.OnlineBanking.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository  extends JpaRepository<ThirdParty, Long> {

    Optional<ThirdParty> findByName(String name);
    Optional<User> findByEmail(String email);

    Optional<ThirdParty> findByHashkey(String hashkey);
}
