package PaloosaBank.OnlineBanking.services.users.interfaces;

import PaloosaBank.OnlineBanking.entities.users.User;

import java.util.List;

public interface UserServiceInterface {


    User getUserById(Long id);
    String deleteUserById(Long id);

    List<User> getAllUsers();
}
