package softuni.exam.models.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class City extends BaseEntity{

    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;

    @Column
    private String description;

    @Column(nullable = false)
    private Integer population;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Forecast> forecasts;

}
