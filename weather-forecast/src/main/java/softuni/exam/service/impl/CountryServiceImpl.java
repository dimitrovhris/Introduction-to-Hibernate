package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/files/json/countries.json");
        byte[] bytes = stream.readAllBytes();
        String jsonContents = new String(bytes);

        return new String(bytes);
    }

    @Override
    public String importCountries() throws IOException {
        List<String> result = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        String json = this.readCountriesFromFile();
        CountryImportDTO[] countryImportDTOS = gson.fromJson(json, CountryImportDTO[].class);
        for (CountryImportDTO countryDTO : countryImportDTOS) {
            if (countryDTO.isValid()) {
                Country country = modelMapper.map(countryDTO, Country.class);
                Optional<Country> existing = countryRepository.findByCountryName(countryDTO.getCountryName());
                if (existing.isPresent()) {
                    result.add("Invalid country");
                } else {
                    countryRepository.save(country);
                    result.add(String.format("Successfully imported country %s - %s", country.getCountryName(), country.getCurrency()));
                }
            } else {
                result.add("Invalid country!");
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
