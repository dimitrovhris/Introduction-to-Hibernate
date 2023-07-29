package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CityImportDTO {

    @Size(min = 2, max = 60)
    @NotNull
    private String cityName;

    @Size(min = 2)
    private String description;

    @Min(500)
    private Integer population;

    private Long country;
}
