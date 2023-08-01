package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "astronomer")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerImportDTO {

    @XmlElement(name = "average_observation_hours")
    private Double averageObservationHours;

    @XmlElement
    private String birthday;

    @XmlElement(name = "first_name")
    private String firstName;

    @XmlElement(name = "last_name")
    private String lastName;

    @XmlElement
    private Double salary;

    @XmlElement(name = "observing_star_id")
    private Long observingStarId;

    public boolean isValid(AstronomerRepository astronomerRepository, StarRepository starRepository) {
        if(astronomerRepository.findByFirstNameAndLastName(firstName, lastName).isEmpty()
        && starRepository.findFirstById(observingStarId).isPresent()
        && firstName.length() >= 2 && firstName.length() <= 30
        && lastName.length() >= 2 && lastName.length() <= 30
        && salary >= 15000 && averageObservationHours > 500){
            return true;
        }
    return false;
    }
}
