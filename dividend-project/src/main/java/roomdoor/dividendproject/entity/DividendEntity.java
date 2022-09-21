package roomdoor.dividendproject.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;
import roomdoor.dividendproject.model.Dividend;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "DIVIDEND")
public class DividendEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private Long companyId;

	private LocalDateTime date;

	private String dividend;


	public DividendEntity(Long companyId, Dividend dividend) {
		this.companyId = companyId;
		this.dividend = dividend.getDividend();
		this.date = dividend.getDate();
	}
}
