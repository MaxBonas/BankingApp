package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Savings extends Account{

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "minimum_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_amount"))
    })
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "fee_amount"))
    })
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40));

    @NotNull(message = "This field can't be null")
    @DecimalMax(value = "0.5", message = "The maximum value for the Interest Rate is 0.5%")
    private Double interestRate = 0.0025;

    private LocalDate lastInterest = LocalDate.now();

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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void setBalance(Money balance) { // Override balance in here allows me to use it in this entity, because is
        // a method that is called every time that u use most complex methods.
        if (balance.getAmount().compareTo(minimumBalance.getAmount()) < 0){
            balance.decreaseAmount(penaltyFee);
        }
        Period period = Period.between(lastInterest, LocalDate.now());
        int yearsPast = period.getYears();
        if (yearsPast >= 1) {
            balance.increaseAmount((balance.getAmount().
                    multiply(new BigDecimal(interestRate)).
                    multiply(new BigDecimal(yearsPast))));
            setLastInterest(LocalDate.now());
        }
        super.setBalance(balance);
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public LocalDate getLastInterest() {
        return lastInterest;
    }

    public void setLastInterest(LocalDate lastInterest) {
        this.lastInterest = lastInterest;
    }
}
