package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "minimum_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_amount"))
    })
    private Money minimumBalance = new Money(BigDecimal.valueOf(250));//TODO en el primero no hace falta @Attribute..

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "fee_amount"))
    })
    private Money penaltyFee = new Money(BigDecimal.valueOf(40));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency", column = @Column(name = "monthly_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_amount"))
    })
    private Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));


    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status);
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

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }
}
