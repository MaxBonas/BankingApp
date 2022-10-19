package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.tools.HashkeyGenerator;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ThirdParty extends User{

    String hashkey;

    public ThirdParty(String name) {
        super(name);
        setHashkey(hashkey);
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
