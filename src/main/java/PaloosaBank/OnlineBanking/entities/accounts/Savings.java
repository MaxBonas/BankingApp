package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;

import javax.validation.constraints.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Savings extends Account{

    @Embedded
    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    @DecimalMin(value = "1000.00", message = "A Savings Account can't be created with less than 1000.00")
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));
    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    @DecimalMax(value = "0.5", message = "The maximum value for the Interest Rate is 0.5%")
    @DecimalMin(value = "0.00", message = "The minimum value for the interest Rate is 0.00%")
    private double interestedRate = 0.0025;

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public Savings() {
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public double getInterestedRate() {
        return interestedRate;
    }

    public void setInterestedRate(double interestedRate) {
        this.interestedRate = interestedRate;
    }
}
