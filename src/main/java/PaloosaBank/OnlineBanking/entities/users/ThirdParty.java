package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.tools.HashkeyGenerator;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class ThirdParty extends User{

    String hashkey;

    public ThirdParty(String name, String email, String password) {
        super(name, email, password);
        setHashkey(hashkey);
        getRoles().add(new Role("THIRD", this));
    }

    public ThirdParty() {
    }

    public String getHashkey() {
        return hashkey;
    }

    public void setHashkey(String hashkey) {
        this.hashkey = HashkeyGenerator.generateString();
    }
}
