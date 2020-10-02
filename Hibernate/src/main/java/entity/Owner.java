package entity;

import util.RatingAttributeConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//? Address bilgilerini ikinci bir tabloda tutmak için
@SecondaryTable(name = "owner_address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "owner_id"))
@SequenceGenerator(name = "seqGen", sequenceName = "owner_seq")
public class Owner extends Person{

    @Embedded
    @Column(name = "address")
    private Address address;

    //@Enumerated(EnumType.ORDINAL)
    @Convert(converter = RatingAttributeConverter.class)
    private Rating rating;

    public Owner (){}

    public Owner(String fullname, Address address, Rating rating){
        super(fullname);
        this.address = address;
        this.rating = rating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + getId() +
                ", fullname='" + getFullname() + '\'' +
                ", address=" + address +
                '}';
    }

    public enum Rating {

        STANDART(100), PREMIUM(200);

        private final int value;

        private Rating(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }
}
