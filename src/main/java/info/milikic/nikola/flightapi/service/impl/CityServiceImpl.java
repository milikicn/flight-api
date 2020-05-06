package info.milikic.nikola.flightapi.service.impl;

import info.milikic.nikola.flightapi.controller.dto.CityRequest;
import info.milikic.nikola.flightapi.controller.dto.CommentRequest;
import info.milikic.nikola.flightapi.persistence.model.City;
import info.milikic.nikola.flightapi.persistence.model.Comment;
import info.milikic.nikola.flightapi.persistence.repository.CityRepository;
import info.milikic.nikola.flightapi.persistence.repository.CommentRepository;
import info.milikic.nikola.flightapi.service.CityService;
import info.milikic.nikola.flightapi.service.exceptions.CityAlreadyExistException;
import info.milikic.nikola.flightapi.service.exceptions.NotFoundException;
import info.milikic.nikola.flightapi.vo.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public City createCity(CityRequest cityRequest) {
        if (cityExists(cityRequest.getName(), cityRequest.getCountry())) {
            throw new CityAlreadyExistException(MessageFormat.format("City with the name {0} already exists", cityRequest.getName()));
        }
        return cityRepository.save(toCity(cityRequest));
    }

    private boolean cityExists(String name, String country) {
        return cityRepository.findByNameAndCountry(name, country).isPresent();
    }

    @Override
    @Transactional
    public Comment createComment(Integer cityId, CommentRequest commentRequest) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CITY_NOT_FOUND, MessageFormat.format("City not found with id {0}", cityId)));

        Comment comment = new Comment();
        comment.setDescription(commentRequest.getDescription());
        comment.setCreated(LocalDateTime.now());
        comment.setModified(comment.getCreated());
        comment.setCity(city);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    @Transactional
    public Comment updateComment(Integer cityId, Integer commentId, CommentRequest commentUpdateRequest) {
        // only checking whether the proper city id is sent, we don't need it for loading the comment
        cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CITY_NOT_FOUND, MessageFormat.format("City not found with id {0}", cityId)));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND, MessageFormat.format("Comment not found with id {0}", commentId)));

        comment.setDescription(commentUpdateRequest.getDescription());
        comment.setModified(LocalDateTime.now());
        return comment;
    }

    @Override
    @Transactional
    public void deleteComment(Integer cityId, Integer commentId) {
        // only checking whether the proper city id is sent, we don't need it for loading the comment
        cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.CITY_NOT_FOUND, MessageFormat.format("City not found with id {0}", cityId)));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND, MessageFormat.format("Comment not found with id {0}", commentId)));

        commentRepository.delete(comment);
    }

    @Override
    public List<City> getAllCities(Integer maxComments) {
        return maxComments != null ? cityRepository.getAll(maxComments) : cityRepository.getAll();
    }

    @Override
    public List<City> searchCities(String query, Integer maxComments) {
        return maxComments != null ? cityRepository.search(query, maxComments) : cityRepository.search("%"+query+"%");
    }

    @Override
    public Integer getCityId(CityRequest cityRequest) {
        return cityRepository.findCityIdByNameAndCountry(cityRequest.getName(), cityRequest.getCountry()).orElse(null);
    }

    private City toCity(CityRequest cityRequest) {
        City city = new City();
        city.setName(cityRequest.getName());
        city.setCountry(cityRequest.getCountry());
        city.setDescription(cityRequest.getDescription());
        return city;
    }
}