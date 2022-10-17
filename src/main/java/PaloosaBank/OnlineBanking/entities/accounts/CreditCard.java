package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditCard extends Account{

    @Embedded
    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    @DecimalMin(value = "100.00", message = "The minimum value for the Credit Limit is 100.00")
    @DecimalMax(value = "100000.00", message = "The minimum value for the Credit Limit is 100000.00")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))
    })
    private Money creditLimit = new Money(BigDecimal.valueOf(100));

    @Embedded
    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "fee_amount"))
    })
    private Money penaltyFee = new Money(BigDecimal.valueOf(40));

    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    @DecimalMax(value = "0,2", message = "The Interest Rate can't be higher that 0,2%")
    @DecimalMin(value = "0,1", message = "The Interest Rate can't be higher that 0,1%")
    private double interestRate = 0.2;

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public CreditCard() {

    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
