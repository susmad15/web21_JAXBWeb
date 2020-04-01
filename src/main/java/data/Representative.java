package data;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = "Representative.getByCountry", query = "SELECT r FROM Representative r WHERE r.country.name = :countryName")
@XmlRootElement
public class Representative {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country ;
    
    // 1)
    @OneToMany(mappedBy = "representative", cascade=CascadeType.PERSIST)
    List<Customer> customers = new ArrayList<>();

    // 2) getter and setter for JAXB
    public Representative(String name) {
        this.name = name;
    }
    
    // 3) add
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    
    public Representative() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @XmlElement
    public List<Customer> getCustomers() {
        return customers;
    }
     
    
    
}
