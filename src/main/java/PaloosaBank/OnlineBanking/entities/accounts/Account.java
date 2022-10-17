package PaloosaBank.OnlineBanking.entities.accounts;

import PaloosaBank.OnlineBanking.embedables.Money;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;
import PaloosaBank.OnlineBanking.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Money balance;
//    private int secretKey; //TODO { Quiza se usa. Si no es del authen de Postman

    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_owner_ID")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_ID")
    private AccountHolder secondaryOwner;

    private LocalDate creationDate;

    private Status status;

    public Account(Money balance, AccountHolder primaryOwner,
                   AccountHolder secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE; // Quiza lo tocas para la validacion de cuentas
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
}
