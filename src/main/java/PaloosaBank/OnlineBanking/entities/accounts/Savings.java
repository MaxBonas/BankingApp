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
public class Savings extends Account{

    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));
    private double interestedRate = 0.0025; //Max 0,5

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Status status, Money minimumBalance, double interestedRate) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status);
        this.minimumBalance = minimumBalance;
        this.interestedRate = interestedRate;
    }

    public Savings() {
    }
}
