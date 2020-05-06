package info.milikic.nikola.flightapi.controller.assembler;

import info.milikic.nikola.flightapi.controller.dto.CityResponse;
import info.milikic.nikola.flightapi.persistence.model.City;

import java.util.List;
import java.util.stream.Collectors;

public class CityAssembler {

    public static CityResponse createResponse(City city) {
        CityResponse response = new CityResponse();
        response.setId(city.getId());
        response.setName(city.getName());
        response.setCountry(city.getCountry());
        response.setDescription(city.getDescription());
        response.setComments(city.getComments().stream()
                .map(CommentAssembler::createResponse)
                .collect(Collectors.toList()));
        return response;
    }

    public static List<CityResponse> createResponse(List<City> cities) {
        return cities.stream().map(CityAssembler::createResponse).collect(Collectors.toList());
    }
}
