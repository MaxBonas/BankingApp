package PaloosaBank.OnlineBanking.embedables;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String postAddress;
    private String city;
    private String postalCode;

    public Address(String postAddress, String city, String postalCode) {
        this.postAddress = postAddress;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address() {
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
