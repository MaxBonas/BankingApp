package PaloosaBank.OnlineBanking.repositoriesTest.users;

import PaloosaBank.OnlineBanking.entities.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long> {

    Optional<Admin> findByName(String name);
}
