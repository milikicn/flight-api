package info.milikic.nikola.flightapi.service.importing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirportImportDto {

    private Integer id;
    private String name;
    private String city;
    private String country;
    private String iata;
    private String icao;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private String timezoneStr;
    private String dst;
    private String tzTime;
    private String type;
    private String source;

    // resolved field
    private Integer cityId;
    private Double timezone;

}
