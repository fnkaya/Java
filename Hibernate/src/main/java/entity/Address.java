package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address{

    //? Address bilgilerini ikinci bir tabloda tutmak için
    @Column(name = "address", table = "owner_address")
    private String address;

    @Column(name = "phone", table = "owner_address")
    private String phone;

    public Address() {
    }

    public Address(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}