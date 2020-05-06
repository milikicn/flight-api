package info.milikic.nikola.flightapi.persistence.repository;

import com.google.common.collect.Lists;
import info.milikic.nikola.flightapi.persistence.model.City;
import info.milikic.nikola.flightapi.persistence.model.Comment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Component
public class CityCustomRepositoryImpl implements CityCustomRepository {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<City> getAll(int maxComments) {
        Query query = em.createNamedQuery("City.getAllCitiesFetchCommentsWithLimit");
        query.setParameter("maxComments", maxComments);

        List<City> cities = parseResultsIntoCities(query);

        return cities;
    }

    @Override
    public List<City> search(String searchQuery, int maxComments) {
        Query query = em.createNamedQuery("City.searchCitiesFetchCommentsWithLimit");
        query.setParameter("maxComments", maxComments);
        query.setParameter("query", "%"+searchQuery+"%");

        List<City> cities = parseResultsIntoCities(query);

        return cities;
    }

    private List<City> parseResultsIntoCities(Query query) {
        List<City> cities = Lists.newArrayList();
        City city = null;

        for (Object o : query.getResultList()) {
            Object[] row = (Object[]) o;

            Integer cityId = (Integer) row[0];

            if (city == null || !cityId.equals(city.getId())) {
                city = new City();
                city.setId(cityId);
                city.setName((String) row[1]);
                city.setCountry((String) row[2]);
                city.setDescription((String) row[3]);
                cities.add(city);
            }

            Comment comment = new Comment();
            comment.setId((Integer) row[4]);
            comment.setDescription((String) row[5]);
            Timestamp created = (Timestamp) row[6];
            comment.setCreated(created.toLocalDateTime());
            Timestamp modified = (Timestamp) row[7];
            comment.setModified(modified.toLocalDateTime());

            city.getComments().add(comment);
        }
        return cities;
    }
}
