package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerImportDTO;
import softuni.exam.models.dto.AstronomerImportWrapperDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
// TODO: Implement all methods
public class AstronomerServiceImpl implements AstronomerService {
    private AstronomerRepository astronomerRepository;
    private StarRepository starRepository;
    private XmlParser xmlParser;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, XmlParser xmlParser) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.xmlParser = xmlParser;

    }

    @Override
    public boolean areImported() {
        return astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(new File("src/main/resources/files/xml/astronomers.xml").toPath());
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        List<String> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        AstronomerImportWrapperDTO astronomerImportWrapperDTO = xmlParser.fromFile(Path.of("src/main/resources/files/xml/astronomers.xml").toFile(), AstronomerImportWrapperDTO.class);
        List<AstronomerImportDTO> astronomerImportDTOS = astronomerImportWrapperDTO.getAstronomers();
        for (AstronomerImportDTO astronomer : astronomerImportDTOS) {
            if(astronomer.isValid(astronomerRepository, starRepository)){
                result.add(String.format(Locale.US, "Successfully imported astronomer %s %s - %.2f", astronomer.getFirstName(), astronomer.getLastName(), astronomer.getAverageObservationHours()));
                Astronomer realAstronomer = modelMapper.map(astronomer, Astronomer.class);
                realAstronomer.setBirthday(LocalDate.parse(astronomer.getBirthday(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                realAstronomer.setObservingStar(starRepository.getById(astronomer.getObservingStarId()));
                starRepository.findFirstById(astronomer.getObservingStarId()).get().getObservers().add(realAstronomer);
                astronomerRepository.saveAndFlush(realAstronomer);
            } else {
                result.add("Invalid astronomer");
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
