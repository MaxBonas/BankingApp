package PaloosaBank.OnlineBanking.DTOs;

import com.sun.istack.NotNull;

public class AccountPostDTO {

    @NotNull
    private Long primaryOwnerId;

    private Long secondaryOwnerId;

    private Double balance;

    private Double minimumBalance;

    private Double monthlyFee;

    private Double creditLimit;

    private Double interestRate;

    public AccountPostDTO(Long primaryOwnerId, Long secondaryOwnerId, Double balance, Double minimumBalance, Double monthlyFee, Double creditLimit, Double interestRate) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.monthlyFee = monthlyFee;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
