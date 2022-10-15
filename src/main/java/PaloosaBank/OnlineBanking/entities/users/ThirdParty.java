package PaloosaBank.OnlineBanking.entities.users;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User{

    String hashKey;

    public ThirdParty(String name, String hashKey) {
        super(name);
        this.hashKey = hashKey;
    }

    public ThirdParty() {
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
