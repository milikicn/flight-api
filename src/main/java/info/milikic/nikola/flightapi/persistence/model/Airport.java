package info.milikic.nikola.flightapi.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", insertable = false, updatable = false, referencedColumnName = "id")
    private City city;

    @Column(name = "iata", length = 3)
    private String iata;

    @Column(name = "icao", length = 4)
    private String icao;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "altitude", nullable = false)
    private Double altitude;

    @Column(name = "timezone")
    private Integer timezone;

    @Column(name = "dst", length = 10)
    private String dst;

    @Column(name = "tz_time", length = 30)
    private String tzTime;

    @Column(name = "type", length = 30, nullable = false)
    private String type;

    @Column(name = "source", length = 30, nullable = false)
    private String source;

}
