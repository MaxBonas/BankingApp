package PaloosaBank.OnlineBanking.DTOs.accounts;

import PaloosaBank.OnlineBanking.enums.Status;
import com.sun.istack.NotNull;

import java.time.LocalDate;

public class AccountGetDTO {

    private Long id;
    @NotNull
    private String primaryOwnerName;

    private Double balance;

    private Status status;

    private LocalDate creationDate;

    public AccountGetDTO(Long id, String primaryOwnerName, Double balance, Status status, LocalDate creationDate) {
        this.id = id;
        this.primaryOwnerName = primaryOwnerName;
        this.balance = balance;
        this.status = status;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimaryOwnerName() {
        return primaryOwnerName;
    }

    public void setPrimaryOwnerName(String primaryOwnerName) {
        this.primaryOwnerName = primaryOwnerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
