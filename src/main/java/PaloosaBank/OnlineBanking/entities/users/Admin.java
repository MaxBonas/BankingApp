package PaloosaBank.OnlineBanking.entities.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{



    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public Admin() {
    }

}
