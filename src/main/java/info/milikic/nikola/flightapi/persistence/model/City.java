package info.milikic.nikola.flightapi.persistence.model;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "city")
@Immutable
@NamedNativeQueries({
        @NamedNativeQuery(name = "City.getAllCitiesFetchCommentsWithLimit", query = "SELECT city_id, city_name, city_country, city_description, comment_id, comment_desc, comment_created, comment_modified FROM (SELECT c.id AS city_id, c.name AS city_name, c.country AS city_country, c.description AS city_description, com.id AS comment_id, com.description AS comment_desc, com.created AS comment_created, com.modified AS comment_modified, row_number() OVER (PARTITION BY c.id) AS rn FROM city c LEFT JOIN comment com ON com.city_id = c.id ) sub WHERE rn <= :maxComments ORDER  BY city_name, comment_created DESC;"),
        @NamedNativeQuery(name = "City.searchCitiesFetchCommentsWithLimit", query = "SELECT city_id, city_name, city_country, city_description, comment_id, comment_desc, comment_created, comment_modified FROM (SELECT c.id AS city_id, c.name AS city_name, c.country AS city_country, c.description AS city_description, com.id AS comment_id, com.description AS comment_desc, com.created AS comment_created, com.modified AS comment_modified, row_number() OVER (PARTITION BY c.id) AS rn FROM city c LEFT JOIN comment com ON com.city_id = c.id WHERE c.name LIKE :query) sub WHERE rn <= :maxComments ORDER  BY city_name, comment_created DESC;")
})
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "city", orphanRemoval = true)
    private List<Comment> comments = Lists.newArrayList();

    @Version
    private int version;

}
