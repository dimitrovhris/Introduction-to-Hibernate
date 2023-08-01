package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDTO;
import softuni.exam.models.dto.StarImportDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;

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
public class ConstellationServiceImpl implements ConstellationService {
    private ConstellationRepository constellationRepository;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    @Override
    public boolean areImported() {
        return constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(new File("src/main/resources/files/json/constellations.json").toPath());
    }

    @Override
    public String importConstellations() throws IOException {
        //INSTRUMENTS:
        List<String> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        Gson gson = new Gson();

        ConstellationImportDTO[] constellationImportDTOS = gson.fromJson(readConstellationsFromFile(), ConstellationImportDTO[].class);
        for (ConstellationImportDTO constellation: constellationImportDTOS) {
            if(constellation.isValid(constellationRepository)){
                Constellation realConstellation = modelMapper.map(constellation, Constellation.class);
                result.add(String.format(Locale.US, "Successfully imported constellation %s - %s", constellation.getName(), constellation.getDescription()));
                constellationRepository.saveAndFlush(realConstellation);
            } else{
                result.add("Invalid constellation");
            }
        }

        return String.join(System.lineSeparator(), result);

    }
}
