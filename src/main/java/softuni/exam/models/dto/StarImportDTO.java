package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.enums.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StarImportDTO implements Serializable {
    private StarRepository starRepository;
    private ConstellationRepository constellationRepository;

    private String description;

    @Min(1)
    private Double lightYears;

    @Size(min = 2, max = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    private StarType starType;

    private Long constellation;

    public boolean isValid(StarRepository starRepository) {
        if (starRepository.findFirstByName(this.name).isEmpty()
                && this.description.length() >= 6 && this.lightYears >= 1 &&
        this.name.length() >= 2 && this.name.length() <= 30
                && (this.starType.equals(StarType.NEUTRON_STAR) || this.starType.equals(StarType.RED_GIANT) || this.starType.equals(StarType.WHITE_DWARF)))
        {
            return true;
        }
        return false;
    }
}
