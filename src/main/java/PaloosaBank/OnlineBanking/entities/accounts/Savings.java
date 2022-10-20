package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Savings extends Account{

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "minimum_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_amount"))
    })
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));

    @NotNull(message = "This field can't be null")
    @DecimalMax(value = "0.5", message = "The maximum value for the Interest Rate is 0.5%")
    private Double interestedRate = 0.0025;

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public Savings() {
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if (minimumBalance.getAmount().compareTo(BigDecimal.valueOf(1000)) < 0)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "A Savings Account can't be created with less than 1000.00");
        this.minimumBalance = minimumBalance;
    }

    public Double getInterestedRate() {
        return interestedRate;
    }

    public void setInterestedRate(Double interestedRate) {
        this.interestedRate = interestedRate;
    }

    @Override
    public void setBalance(Money balance) { // todo penlty fee
        super.setBalance(balance);
    }
}
