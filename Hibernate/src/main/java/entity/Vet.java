package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seqGen", sequenceName = "vet_seq")
public class Vet extends Person {

    @Column(name = "works_full_time")
    private Boolean worksFullTime;

    public Vet() {
    }

    public Vet(String fullname, Boolean worksFullTime) {
        super(fullname);
        this.worksFullTime = worksFullTime;
    }

    public Boolean getWorksFullTime() {
        return worksFullTime;
    }

    public void setWorksFullTime(Boolean worksFullTime) {
        this.worksFullTime = worksFullTime;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id=" + getId() +
                ", fullname='" + getFullname() + '\'' +
                ", worksFullTime=" + worksFullTime +
                '}';
    }
}
