package data;

import adapters.DateAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Pascal Klug
 */
//@XmlType(propOrder = {"name", "capital", "foundation", "continent", "importance", "population"})
//@XmlRootElement(name = "Country")
@Entity
@NamedQuery(name = "Country.getAll", query = "SELECT DISTINCT c FROM Country c ORDER BY c.name")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private int population;
    private String name;
    private String capital;
    private String continent;
    private int importance;
    private LocalDate foundation;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private List<Representative> representatives = new ArrayList();

    public void addRepresentatives(Representative rep) {
        representatives.add(rep);
    }

    public int getPopulation() {
        return population;
    }

    @XmlElement(name = "Country_Population")
    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Country_Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    @XmlElement(name = "Country_Capital")
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    @XmlElement(name = "Country_Continent")
    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getImportance() {
        return importance;
    }

    @XmlElement(name = "importance")
    public void setImportance(int importance) {
        this.importance = importance;
    }

    public LocalDate getFoundation() {
        return foundation;
    }

    @XmlElement(name = "Country_Foundation_Date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    public List<Representative> getRepresentatives() {
        return representatives;
    }

    @XmlElement(name = "Representative")
    @XmlElementWrapper(name = "Representatives")
    public void setRepresentatives(List<Representative> representatives) {
        this.representatives = representatives;
    }

}
