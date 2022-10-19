package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Checking extends Account{

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "minimum_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_amount"))
    })
    private Money minimumBalance = new Money(BigDecimal.valueOf(250));

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "fee_amount"))
    })
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40));

    @Embedded
    @NotNull(message = "This field can't be null")
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "monthly_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_amount"))
    })
    private Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));


    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public Checking() {
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    @Override
    public void setBalance(Money balance) {
        super.setBalance(balance);
    }
}
