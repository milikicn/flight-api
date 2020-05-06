package info.milikic.nikola.flightapi.service;

import info.milikic.nikola.flightapi.controller.dto.CityRequest;
import info.milikic.nikola.flightapi.controller.dto.CommentRequest;
import info.milikic.nikola.flightapi.persistence.model.City;
import info.milikic.nikola.flightapi.persistence.model.Comment;

import java.util.List;

public interface CityService {

    City createCity(CityRequest cityRequest);

    Comment createComment(Integer cityId, CommentRequest commentRequest);

    Comment updateComment(Integer cityId, Integer commentId, CommentRequest commentUpdateRequest);

    void deleteComment(Integer cityId, Integer commentId);

    List<City> getAllCities(Integer maxComments);

    List<City> searchCities(String query, Integer maxComments);

    Integer getCityId(CityRequest cityRequest);
}
