package info.milikic.nikola.flightapi.persistence.repository;

import info.milikic.nikola.flightapi.persistence.model.City;

import java.util.List;

public interface CityCustomRepository{

    List<City> getAll(int maxComments);

    List<City> search(String searchQuery, int maxComments);

}
