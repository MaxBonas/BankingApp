package PaloosaBank.OnlineBanking.services.users.interfaces;

import PaloosaBank.OnlineBanking.entities.users.User;

import java.util.List;

public interface UserServiceInterface {

    User addUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id);
}
