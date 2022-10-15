package PaloosaBank.OnlineBanking.controllers.users.interfaces;

import PaloosaBank.OnlineBanking.entities.users.Admin;
import PaloosaBank.OnlineBanking.entities.users.User;

import java.util.List;

public interface UserControllerInterface {

    User addUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id);
}
