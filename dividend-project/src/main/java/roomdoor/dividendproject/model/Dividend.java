package roomdoor.dividendproject.model;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dividend {

	private LocalDateTime date;
	private String dividend;

}
