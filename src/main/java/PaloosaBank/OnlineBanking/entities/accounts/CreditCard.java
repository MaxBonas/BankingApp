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
public class CreditCard extends Account{

    private Money creditLimit = new Money(BigDecimal.valueOf(250));

    private Money penaltyFee = new Money(BigDecimal.valueOf(40));

    private double interestRate = 12;

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                      LocalDate creationDate, Status status, Money creditLimit, Money penaltyFee, double interestRate) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status);
        this.creditLimit = creditLimit;
        this.penaltyFee = penaltyFee;
        this.interestRate = interestRate;
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
