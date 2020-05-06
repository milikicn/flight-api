package info.milikic.nikola.flightapi.persistence.repository;

import info.milikic.nikola.flightapi.persistence.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>, CityCustomRepository {

    Optional<City> findByNameAndCountry(String name, String country);

    @Query("SELECT c.id FROM City c WHERE c.name = :name AND c.country = :country")
    Optional<Integer> findCityIdByNameAndCountry(String name, String country);

    @Query("SELECT DISTINCT c FROM City c LEFT JOIN FETCH c.comments")
    List<City> getAll();

    @Query("SELECT DISTINCT c FROM City c LEFT JOIN FETCH c.comments WHERE c.name LIKE :query")
    List<City> search(@Param("query") String searchQuery);

}
