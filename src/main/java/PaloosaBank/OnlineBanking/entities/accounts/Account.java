package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.Transfer;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;
import PaloosaBank.OnlineBanking.tools.PasswordHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Money balance;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String secretKey;

    @NotNull
//    @NotBlank
    @ManyToOne
    @JoinColumn(name = "primary_owner_ID")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_ID")
    private AccountHolder secondaryOwner;

    @OneToMany(mappedBy = "senderAccount")
    @JsonIgnore
    private List<Transfer> senderTransfers;

    @NotNull
//    @NotBlank
    private LocalDate creationDate;

    @NotNull
//    @NotBlank
    private Status status = Status.ACTIVE; // Quiza lo tocas para la validacion de cuentas

    public Account(Money balance, AccountHolder primaryOwner,
                   AccountHolder secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = LocalDate.now();
        this.secretKey = PasswordHelper.generatePassword();
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public List<Transfer> getSenderTransfers() {
        return senderTransfers;
    }

    public void setSenderTransfers(List<Transfer> senderTransfers) {
        this.senderTransfers = senderTransfers;
    }

}
