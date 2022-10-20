package PaloosaBank.OnlineBanking.DTOs.accounts;

public class PaymentTPGetDTO {

    private Long id;

    private String primaryOwnerName;

    private Double amount;

    public PaymentTPGetDTO(Long id, String primaryOwnerName, Double amount) {
        this.id = id;
        this.primaryOwnerName = primaryOwnerName;
        this.amount = amount;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
