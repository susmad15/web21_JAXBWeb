package server;

import data.Countries;
import data.Country;
import data.Representative;
import java.util.List;

public interface IServerProxy {

    public void persistCountries(Countries countries);

    public List<Country> getAllCountries();

    public List<Representative> getRepresentativesOfCountry(String countryName);

}
