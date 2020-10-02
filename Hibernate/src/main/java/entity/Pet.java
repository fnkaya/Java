package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "seqGen", sequenceName = "pet_seq")
public class Pet extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public Pet() {
    }

    public Pet(String name, Date birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    public Pet(Long id, String name, Date birthDate) {
        this(name, birthDate);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
