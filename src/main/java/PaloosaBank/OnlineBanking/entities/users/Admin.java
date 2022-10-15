package PaloosaBank.OnlineBanking.entities.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin(String name) {
        super(name);
    }

    public Admin() {
    }
}
