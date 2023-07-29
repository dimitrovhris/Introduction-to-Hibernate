package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Forecast;

// TODO:
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

}
