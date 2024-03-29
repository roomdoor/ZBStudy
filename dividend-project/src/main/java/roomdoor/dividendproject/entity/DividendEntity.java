package roomdoor.dividendproject.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roomdoor.dividendproject.model.Dividend;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "DIVIDEND")
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"companyId", "date"}
		)
	}
)
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
