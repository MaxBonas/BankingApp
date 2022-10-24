package PaloosaBank.OnlineBanking.services.users;

import PaloosaBank.OnlineBanking.entities.users.User;
import PaloosaBank.OnlineBanking.repositories.users.UserRepository;
import PaloosaBank.OnlineBanking.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsSevice implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Name does not exist.");
        }
        return new CustomUserDetails(user.get());
    }
}
