package PaloosaBank.OnlineBanking.DTOs;

import java.math.BigDecimal;

public class TransferGetDTO {

    private String ownerName;

    private String recipientName;

    private BigDecimal amount;

    public TransferGetDTO(String ownerName, String recipientName, BigDecimal amount) {
        this.ownerName = ownerName;
        this.recipientName = recipientName;
        this.amount = amount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
