package softuni.exam.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import softuni.exam.enums.DayOfWeek;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Table(name = "countries")
@Entity
public class Country extends BaseEntity{

    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Column(name = "currency", nullable = false)
    private String currency;

    @OneToMany(mappedBy = "country")
    Set<City> cities;
}
