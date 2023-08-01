package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.enums.StarType;
import softuni.exam.models.dto.StarImportDTO;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

// TODO: Implement all methods
@Service
public class StarServiceImpl implements StarService {
    private StarRepository starRepository;
    private ConstellationRepository constellationRepository;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean areImported() {
        return starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(new File("src/main/resources/files/json/stars.json").toPath());

    }

    @Override
    public String importStars() throws IOException {
        List<String> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        Gson gson = new Gson();

        StarImportDTO[] starImportDTOS = gson.fromJson(this.readStarsFileContent(), StarImportDTO[].class);
        for (StarImportDTO star : starImportDTOS) {
            if (star.isValid(starRepository)) {
                result.add(String.format(Locale.US, "Successfully imported star %s - %.2f light years", star.getName(), star.getLightYears()));
                Star realStar = modelMapper.map(star, Star.class);
                realStar.setConstellation(constellationRepository.findFirstById(star.getConstellation()).get());
                constellationRepository.findFirstById(star.getConstellation()).get().getStars().add(realStar);
                starRepository.saveAndFlush(realStar);
            } else{
                result.add("Invalid star");
            }
        }
        return String.join(System.lineSeparator(), result);
    }

    @Override
    public String exportStars() {
        List<String> result = new ArrayList<>();
        Set<Star> stars = starRepository.findByStarTypeOrderByLightYears(StarType.RED_GIANT);
        for (Star star : stars) {
            if(star.getObservers().size() == 0){
                result.add(String.format(Locale.US,"Star: %s%n" +
                        "   *Distance: %.2f light years%n" +
                        "   **Description: %s%n" +
                        "   ***Constellation: %s", star.getName(), star.getLightYears(), star.getDescription(), star.getConstellation().getName()));
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
