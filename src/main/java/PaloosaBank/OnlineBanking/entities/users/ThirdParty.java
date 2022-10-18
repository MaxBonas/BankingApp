package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.tools.HashkeyGenerator;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ThirdParty extends User{

//    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
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
