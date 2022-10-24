package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account{

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))
    })
    private Money creditLimit = new Money(BigDecimal.valueOf(100));

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "fee_amount"))
    })
    private Money penaltyFee = new Money(BigDecimal.valueOf(40));

    @NotNull(message = "This field can't be null")
    @DecimalMax(value = "0.2", message = "The Interest Rate can't be higher than 0,2%")
    @DecimalMin(value = "0.1", message = "The Interest Rate can't be higher than 0,1%")
    private Double interestRate = 0.2;

    private LocalDate lastInterest = LocalDate.now();

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public CreditCard() {

    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) > 0)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "The Credit Card Limit can't be higher than 100.000");
        if (creditLimit.getAmount().compareTo(BigDecimal.valueOf(100)) < 0)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "The Credit Card Limit can't be lower than 100");
        this.creditLimit = creditLimit;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getLastInterest() {
        return lastInterest;
    }

    public void setLastInterest(LocalDate lastInterest) {
        this.lastInterest = lastInterest;
    }

    @Override
    public void setBalance(Money balance) { // Override balance in here allows me to use it in this entity, because is
        // a method that is called every time that u use most complex methods.
        Period period = Period.between(lastInterest, LocalDate.now());
        int yearsPast = period.getYears();
        int monthsPast = period.getMonths();
        double monthlyInterest = interestRate / 12;
        if (monthsPast >= 1) {
            balance.decreaseAmount(((balance.getAmount().multiply(new BigDecimal(monthlyInterest)).multiply(new BigDecimal(monthsPast)))));
            setLastInterest(LocalDate.now());
        }

        super.setBalance(balance);
    }
}
