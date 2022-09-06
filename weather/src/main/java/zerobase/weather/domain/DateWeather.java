package zerobase.weather.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DateWeather {

	@Id
	private LocalDate date;
	private String weather;
	private String icon;
	private double temperature;
}
