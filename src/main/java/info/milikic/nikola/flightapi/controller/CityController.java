package info.milikic.nikola.flightapi.controller;

import info.milikic.nikola.flightapi.controller.assembler.CityAssembler;
import info.milikic.nikola.flightapi.controller.assembler.CommentAssembler;
import info.milikic.nikola.flightapi.controller.dto.CityRequest;
import info.milikic.nikola.flightapi.controller.dto.CityResponse;
import info.milikic.nikola.flightapi.controller.dto.CommentRequest;
import info.milikic.nikola.flightapi.controller.dto.CommentResponse;
import info.milikic.nikola.flightapi.service.impl.CityServiceImpl;
import info.milikic.nikola.flightapi.vo.ApplicationRoles;
import info.milikic.nikola.flightapi.vo.ServiceConst;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = ServiceConst.CITY_API)
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @Secured(ApplicationRoles.ROLE_ADMIN)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public CityResponse createCity(
            @ApiParam(value = "Model used to create new city", required = true) @Valid @RequestBody CityRequest cityRequest) {
        log.debug("Create new city with information: {}", cityRequest);

        return CityAssembler.createResponse(cityService.createCity(cityRequest));
    }

    @Secured(ApplicationRoles.ROLE_USER)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityResponse> getAllCities(@RequestParam(value = "maxComments", required = false) Integer maxComments) {
        log.debug("Retrieving all cities with max comment number {}", maxComments);

        return CityAssembler.createResponse(cityService.getAllCities(maxComments));
    }

    @Secured(ApplicationRoles.ROLE_USER)
    @GetMapping(
            value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityResponse> searchCities(@RequestParam(value = "query") String query,
                                           @RequestParam(value = "maxComments", required = false) Integer maxComments) {
        log.debug("Searching all cities with query {} max comment number {}", query, maxComments);

        return CityAssembler.createResponse(cityService.searchCities(query, maxComments));
    }

    @Secured(ApplicationRoles.ROLE_USER)
    @PostMapping(
            value = "/{id}/comment",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentResponse addComment(
            @ApiParam(value = "City id", required = true) @PathVariable("id") Integer cityId,
            @ApiParam(value = "Model used to create city comment", required = true) @Valid @RequestBody CommentRequest commentRequest) {
        log.debug("Create comment for city {} with information: {}", cityId, commentRequest);

        return CommentAssembler.createResponse(cityService.createComment(cityId, commentRequest));
    }

    @Secured(ApplicationRoles.ROLE_USER)
    @DeleteMapping(
            value = "/{id}/comment/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteComment(
            @ApiParam(value = "City id", required = true) @PathVariable("id") Integer cityId,
            @ApiParam(value = "Comment id", required = true) @PathVariable("commentId") Integer commentId) {
        log.debug("Delete city {} comment {}", cityId, commentId);

        cityService.deleteComment(cityId, commentId);
    }

    @Secured(ApplicationRoles.ROLE_USER)
    @PutMapping(value = "/{id}/comment/{commentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentResponse updateComment(
            @ApiParam(value = "City id", required = true) @PathVariable("id") Integer cityId,
            @ApiParam(value = "Comment id", required = true) @PathVariable("commentId") Integer commentId,
            @ApiParam(value = "Model used to update city comment", required = true) @Valid @RequestBody CommentRequest commentUpdateRequest) {
        log.debug("Update city {} comment {} with information: {}", cityId, commentId, commentUpdateRequest);

        return CommentAssembler.createResponse(cityService.updateComment(cityId, commentId, commentUpdateRequest));
    }

}
