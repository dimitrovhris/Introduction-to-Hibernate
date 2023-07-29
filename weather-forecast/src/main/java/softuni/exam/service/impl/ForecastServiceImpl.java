package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastImportDTO;
import softuni.exam.models.dto.ForecastWrapperDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ForecastServiceImpl implements ForecastService {
    private ForecastRepository forecastRepository;
    private CityRepository cityRepository;
    private ModelMapper modelMapper;
    private ValidatorFactory factory;
    private XmlParser xmlParser;

    public ForecastServiceImpl(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
        this.factory = Validation.buildDefaultValidatorFactory();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/files/xml/forecasts.xml");
        byte[] bytes = stream.readAllBytes();
        return new String(bytes);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
//        modelMapper.addConverter((Converter<String, LocalTime>) mappingContext ->
//    LocalTime.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("hh:mm:ss")));

        ForecastWrapperDTO forecastWrapperDTO = xmlParser.fromFile(Path.of("/files/xml/forecasts.xml").toFile(), ForecastWrapperDTO.class);
        List<ForecastImportDTO> forecasts = forecastWrapperDTO.getForecasts();
        List<String> result = new ArrayList<>();
        for (ForecastImportDTO forecast : forecasts) {

            Validator validator = factory.getValidator();
            Set<ConstraintViolation<ForecastImportDTO>> validateForecast = validator.validate(forecast);
            if(validateForecast.size() > 0){
                result.add("Invalid forecast");
            } else {
                Optional<> existing = forecastRepository.findBy(forecast.getCity());

                result.add(String.format("Successfully import forecast %s - %.2f", forecast.getDayOfWeek(), forecast.getMaxTemperature()));
                Forecast realForecast = modelMapper.map(forecast, Forecast.class);
                realForecast.setCity();
            }
        }
        return String.join(System.lineSeparator(), result);
    }

    @Override
    public String exportForecasts() {
        return null;
    }
}
