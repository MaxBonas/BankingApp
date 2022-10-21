package PaloosaBank.OnlineBanking.entities;

import PaloosaBank.OnlineBanking.entities.accounts.Account;
import PaloosaBank.OnlineBanking.entities.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_account_ID")
    private Account senderAccount;
    @ManyToOne
    @JoinColumn(name = "primary_owner_ID")
    private AccountHolder primaryOwner;
    private BigDecimal amount;
    private LocalDate transferDate = LocalDate.now();
    private LocalTime transferTime = LocalTime.now();

    public Transfer(Account senderAccount, AccountHolder primaryOwner, BigDecimal amount) {
        this.senderAccount = senderAccount;
        this.primaryOwner = primaryOwner;
        this.amount = amount;
        setPrimaryOwner(senderAccount.getPrimaryOwner());
    }

    public Transfer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccountOutId() {
        return senderAccount;
    }

    public void setAccountOutId(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalTime getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(LocalTime transferTime) {
        this.transferTime = transferTime;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }
}
