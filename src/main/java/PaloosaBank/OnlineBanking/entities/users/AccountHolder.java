package PaloosaBank.OnlineBanking.entities.users;

import PaloosaBank.OnlineBanking.embedables.Address;
import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.accounts.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends User{

//    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    private LocalDate dateOfBirth;
//    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null") // Esto me permitira dejar la secondary nula o no
    @Embedded
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="postAddress", column = @Column(name = "postadress2")),
            @AttributeOverride(name="city", column = @Column(name = "city2")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postcode2"))
    })
    private Address mailingAddress;

//    @NotBlank(message = "This field can't be blank")
//    @NotNull(message = "This field can't be null") // todo revisar esto!
    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccountList;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccountList;  //TODO {No estoy seguro de que tenga que existir

//    @NotBlank(message = "This field can't be blank")
    @NotNull(message = "This field can't be null")
    private Money monthlySpended = new Money(BigDecimal.valueOf(0)); //Todo como plantear esta suma en cada transaccion

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) { // TODO aqui no va el monthly, no?
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
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
