package info.milikic.nikola.flightapi.controller;

import info.milikic.nikola.flightapi.controller.dto.CityResponse;
import info.milikic.nikola.flightapi.vo.ApplicationRoles;
import info.milikic.nikola.flightapi.vo.ServiceConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = ServiceConst.FLIGHT_API)
public class FlightController {

    @Secured(ApplicationRoles.ROLE_USER)
    @GetMapping(
            value = "/cheapest",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CityResponse getCity(@PathVariable(value = "from") String fromCity,
                                @PathVariable(value = "to") String toCity) {
        log.debug("Finding the cheapest route from city {} to city {}", fromCity, toCity);

        return null;
    }
}
