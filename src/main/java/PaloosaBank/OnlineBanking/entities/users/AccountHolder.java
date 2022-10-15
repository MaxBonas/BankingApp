package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends User{

    private LocalDate dateOfBirth;

//    @Embedded
    private Address primaryAddress;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="currency1", column = @Column(name = "fee_currency1")),
//            @AttributeOverride(name = "amount1", column = @Column(name = "fee_amount1"))
//    })
    private Address mailingAddress; // (Optional)

    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryAccountList;

//    @OneToMany(mappedBy = "secondaryOwner")
//    private List<Account> secondaryAccountList;  //TODO {No estoy seguro de que tenga que existir
    private Money monthlySpended;

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress, Money monthlySpended) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.monthlySpended = monthlySpended;
        //TODO Aqui iria la lista?
    }

    public AccountHolder() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getPrimaryAccountList() {
        return primaryAccountList;
    }

    public void setPrimaryAccountList(List<Account> primaryAccountList) {
        this.primaryAccountList = primaryAccountList;
    }

    public Money getMonthlySpended() {
        return monthlySpended;
    }

    public void setMonthlySpended(Money monthlySpended) {
        this.monthlySpended = monthlySpended;
    }
}
