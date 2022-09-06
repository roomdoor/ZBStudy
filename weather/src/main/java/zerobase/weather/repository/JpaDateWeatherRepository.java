package zerobase.weather.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.DateWeather;

@Repository
public interface JpaDateWeatherRepository extends JpaRepository<DateWeather, LocalDate> {

	Optional<DateWeather> findByDate(LocalDate localDate);
}
