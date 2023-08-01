package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.enums.StarType;
import softuni.exam.models.entity.Star;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// TODO:
@Repository
public interface StarRepository extends JpaRepository<Star, Long> {
    Optional<Star> findFirstByName(String name);

    Optional<Star> findFirstById(Long id);

    Set<Star> findByStarTypeOrderByLightYears(StarType starType);

}
