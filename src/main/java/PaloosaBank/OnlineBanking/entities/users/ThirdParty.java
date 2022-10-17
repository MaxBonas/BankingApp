package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.tools.HashkeyGenerator;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ThirdParty extends User{

    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    String hashKey;

    public ThirdParty(String name) {
        super(name);
        setHashKey(hashKey);
    }

    public ThirdParty() {
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = HashkeyGenerator.generateString();
    }
}
