package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/files/json/cities.json");
        byte[] bytes = stream.readAllBytes();
        return new String(bytes);
    }

    @Override
    public String importCities() throws IOException {
        List<String> result = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        ModelMapper modelMapper = new ModelMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String json = this.readCitiesFileContent();

        CityImportDTO[] cityImportDTOS = gson.fromJson(json, CityImportDTO[].class);

        for (CityImportDTO city : cityImportDTOS) {
            Set<ConstraintViolation<CityImportDTO>> validationResult = validator.validate(city);
            if (validationResult.size() > 0) {
                result.add("Invalid city");
                continue;
            }

            Optional<Country> country = countryRepository.findById(city.getCountry());

            if(country.isEmpty()){
                result.add("Invalid city");
                continue;
            }
            Optional<City> existing = cityRepository.findByCityName(city.getCityName());
            if(existing.isPresent()){
                result.add("Invalid city!");
                continue;
            }

            City cityToBeAdded = modelMapper.map(city, City.class);
            cityToBeAdded.setCountry(country.get());
            result.add(String.format("Successfully imported city %s - %d", cityToBeAdded.getCityName(), cityToBeAdded.getPopulation()));
            cityRepository.saveAndFlush(cityToBeAdded);
        }
        return String.join("\n", result);
    }
}
