package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends User{

    private LocalDate dateOfBirth;
    @NotNull // Esto me permitira dejar la secondary nula o no
    @Embedded
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="postAddress", column = @Column(name = "postadress2")),
            @AttributeOverride(name="city", column = @Column(name = "city2")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postcode2"))
    })
    private Address mailingAddress; // (Optional)

    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryAccountList;

//    @OneToMany(mappedBy = "secondaryOwner")
//    private List<Account> secondaryAccountList;  //TODO {No estoy seguro de que tenga que existir
    private Money monthlySpended = new Money(BigDecimal.valueOf(0));

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) { // TODO aqui no va el monthly, no?
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        //TODO Aqui iria la lista?
    }

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress) { // TODO aqui no va el monthly, no?
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.monthlySpended = new Money(BigDecimal.valueOf(0));
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
