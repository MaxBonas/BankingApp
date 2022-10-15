package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account{

    private Money minimumBalance = new Money(BigDecimal.valueOf(250));
    private Money penaltyFee = new Money(BigDecimal.valueOf(40));
    private Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));


    public Checking(Money balance, AccountHolder primaryOwner,
                    AccountHolder secondaryOwner, LocalDate creationDate,
                    Status status, Money minimumBalance, Money penaltyFee,
                    Money monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status);
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
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
