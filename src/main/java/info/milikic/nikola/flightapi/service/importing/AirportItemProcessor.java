package info.milikic.nikola.flightapi.service.importing;

import info.milikic.nikola.flightapi.controller.dto.CityRequest;
import info.milikic.nikola.flightapi.persistence.model.City;
import info.milikic.nikola.flightapi.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AirportItemProcessor implements ItemProcessor<AirportImportDto, AirportImportDto> {

    private static final String NULL_SYMBOL = "\\N";

    @Autowired
    private CityService cityService;

    @Override
    public AirportImportDto process(final AirportImportDto airportDto) {
        CityRequest cityRequest = new CityRequest()
                .setName(airportDto.getCity())
                .setCountry(airportDto.getCountry());

        City city = cityService.getOrCreateCity(cityRequest);
        airportDto.setCityId(city.getId());

        log.info("For city with name {} and country {} resolved id {}", airportDto.getCity(), airportDto.getCountry(), city.getId());

        if (airportDto.getIata() != null && airportDto.getIata().equals(NULL_SYMBOL)) {
            airportDto.setIata(null);
        }
        if (airportDto.getTimezone() != null && airportDto.getTimezone().equals(NULL_SYMBOL)) {
            airportDto.setTimezone(null);
        }
        if (airportDto.getDst() != null && airportDto.getDst().equals(NULL_SYMBOL)) {
            airportDto.setDst(null);
        }
        if (airportDto.getTzTime() != null && airportDto.getTzTime().equals(NULL_SYMBOL)) {
            airportDto.setTzTime(null);
        }

        return airportDto;
    }

}
