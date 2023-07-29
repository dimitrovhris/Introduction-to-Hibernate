package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.enums.DayOfWeek;
import softuni.exam.models.entity.City;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    @XmlElement(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @DecimalMax(value = "60")
    @DecimalMin(value = "-20")
    @NotNull
    @XmlElement(name = "max_temperature")
    private BigDecimal maxTemperature;

    @DecimalMax(value = "40")
    @DecimalMin(value = "-50")
    @NotNull
    @XmlElement(name = "min_temperature")
    private BigDecimal minTemperature;

    @NotNull
    @XmlElement
    private LocalTime sunrise;

    @NotNull
    @XmlElement
    private LocalTime sunset;

    @NotNull
    @XmlElement
    private Long city;

}
