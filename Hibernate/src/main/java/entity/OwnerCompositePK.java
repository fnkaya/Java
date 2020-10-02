package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "owner_composite_pk")
public class OwnerCompositePK {

    @Id
    @Column(name = "id")
    private OwnerId id;

    public OwnerCompositePK() {
    }

    public OwnerCompositePK(OwnerId id) {
        this.id = id;
    }

    public OwnerId getId() {
        return id;
    }

    public void setId(OwnerId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OwnerCompositePK{" +
                "id=" + id +
                '}';
    }

    @Embeddable
    public static class OwnerId implements Serializable{
        @Column(nullable = false)
        private String firstname;
        @Column(nullable = false)
        private String lastname;

        public OwnerId() {
        }

        public OwnerId(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        @Override
        public String toString() {
            return "OwnerId{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    '}';
        }
    }
}
