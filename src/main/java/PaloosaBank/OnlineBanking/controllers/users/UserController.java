package PaloosaBank.OnlineBanking.controllers.users;

import PaloosaBank.OnlineBanking.controllers.users.interfaces.UserControllerInterface;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.AccountServiceInterface;
import PaloosaBank.OnlineBanking.services.users.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserControllerInterface {

    @Autowired
    UserServiceInterface userServiceInterface;
}
