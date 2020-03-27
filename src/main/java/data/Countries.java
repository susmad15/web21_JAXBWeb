package data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pascal Klug
 */
@XmlRootElement(name = "Countries")
public class Countries {

    private List<Country> countries = new ArrayList();

    public void addCountry(Country country) {
        countries.add(country);
    }
    
    public List<Country> getCountries() {
        return countries;
    }

    @XmlElement(name = "Country")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
