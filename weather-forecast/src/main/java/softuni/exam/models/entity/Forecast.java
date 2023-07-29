package softuni.exam.models.entity;

import lombok.Getter;
import lombok.Setter;
import softuni.exam.enums.DayOfWeek;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@Table(name = "forecasts")
@Entity
public class Forecast extends BaseEntity{
    @Column(name = "day_of_week", nullable = false)
    @Enumerated
    private DayOfWeek dayOfWeek;

    @Column(name = "max_temperature", nullable = false)
    @Min(-20)
    @Max(60)
    private BigDecimal maxTemperature;

    @Column(name = "min_temperature", nullable = false)
    @Min(-50)
    @Max(40)
    private BigDecimal minTemperature;

    @Column(nullable = false)
    private LocalTime sunrise;

    @Column(nullable = false)
    private LocalTime sunset;

    @ManyToOne
    private City city;

}
