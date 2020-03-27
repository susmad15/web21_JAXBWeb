package server;

import data.Countries;
import data.Country;
import data.Customer;
import data.Representative;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DBServerProxy implements IServerProxy{

    private EntityManagerFactory emf;
    private EntityManager em;

    public DBServerProxy() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    public void persistCountries(Countries countries) {

       for (Country country : countries.getCountries()) {
            for (Representative rep : country.getRepresentatives()) {
                rep.setCountry(country);
                //set representative for each customer
                for (Customer customer : rep.getCustomers()) {
                    customer.setRepresentative(rep);
                }
            }
        }

        em.getTransaction().begin();

        countries.getCountries().stream()
                .forEach(c -> em.persist(c));

        em.getTransaction().commit();
    }

    public List<Country> getAllCountries() {
        TypedQuery<Country> query = em.createNamedQuery("Country.getAll", Country.class);
        List<Country> countries = query.getResultList();

        countries.stream().map(c -> " " + c.getName()).forEach(System.out::println);

        return countries;
    }

    public List<Representative> getRepresentativesOfCountry(String countryName) {
        TypedQuery<Representative> query = em.createNamedQuery("Representative.getByCountry", Representative.class);
        query.setParameter("countryName", countryName);
        List<Representative> representatives = query.getResultList();

        representatives.stream().map(r -> " " + r.getName()).forEach(System.out::println);

        return representatives;
    }

}
