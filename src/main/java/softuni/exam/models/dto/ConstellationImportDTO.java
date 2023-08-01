package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import softuni.exam.repository.ConstellationRepository;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ConstellationImportDTO implements Serializable {


    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5)
    private String description;

    public boolean isValid(ConstellationRepository constellationRepository) {
        if (this.name.length() >= 3 && this.name.length() <= 20
                && this.description.length() >= 5
                && constellationRepository.findFirstByName(name).isEmpty()){
            return true;
        }
        return false;
    }
}
