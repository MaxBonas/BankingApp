package PaloosaBank.OnlineBanking.DTOs;

import java.math.BigDecimal;

public class TransferPostDTO {

    private Long accountOutId;
    private Long accountInId;
    private BigDecimal amount;
    private String secretKey;

    public TransferPostDTO(Long accountOutId, Long accountInId, BigDecimal amount, String secretKey) {
        this.accountOutId = accountOutId;
        this.accountInId = accountInId;
        this.amount = amount;
        this.secretKey = secretKey;
    }

    public Long getAccountOutId() {
        return accountOutId;
    }

    public void setAccountOutId(Long accountOutId) {
        this.accountOutId = accountOutId;
    }

    public Long getAccountInId() {
        return accountInId;
    }

    public void setAccountInId(Long accountInId) {
        this.accountInId = accountInId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
