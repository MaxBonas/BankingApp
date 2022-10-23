package PaloosaBank.OnlineBanking.DTOs.users;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserDTO {
    private Long primaryOwnerId;
    private BigDecimal amount;
    private LocalDate transferDate;

    public UserDTO(Long primaryOwnerId, BigDecimal amount, LocalDate transferDate) {
        this.primaryOwnerId = primaryOwnerId;
        this.amount = amount;
        this.transferDate = transferDate;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
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
}
