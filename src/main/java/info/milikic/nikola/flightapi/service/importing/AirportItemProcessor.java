package info.milikic.nikola.flightapi.service.importing;

import info.milikic.nikola.flightapi.controller.dto.CityRequest;
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

        Integer cityId = cityService.getCityId(cityRequest);

        if (cityId == null) {
            return null;
        }

        airportDto.setCityId(cityId);

        log.info("For city with name {} and country {} resolved id {}", airportDto.getCity(), airportDto.getCountry(), cityId);

        if (airportDto.getIata() == null || airportDto.getIata().equals(NULL_SYMBOL)) {
            airportDto.setIata(null);
        }
        if (airportDto.getTimezoneStr() == null || airportDto.getTimezoneStr().equals(NULL_SYMBOL)) {
            airportDto.setTimezone(null);
        } else {
            airportDto.setTimezone(Double.parseDouble(airportDto.getTimezoneStr()));
        }
        if (airportDto.getDst() == null || airportDto.getDst().equals(NULL_SYMBOL)) {
            airportDto.setDst(null);
        }
        if (airportDto.getTzTime() == null || airportDto.getTzTime().equals(NULL_SYMBOL)) {
            airportDto.setTzTime(null);
        }

        return airportDto;
    }

}
