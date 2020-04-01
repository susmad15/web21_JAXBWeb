package data;

import adapters.DateAdapter;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Entity
@XmlRootElement
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    

    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "representative_id")
    private Representative representative;
    
    private Gender gender;

    public Customer(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
    //Warning: do not use reference to antother class in toString

    public LocalDate getBirthday() {
        return birthday;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}