package roomdoor.dividendproject.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ScrapedResult {

	private Company company;

	private List<Dividend> dividendEntities;

	public ScrapedResult() {
		this.dividendEntities = new ArrayList<>();
	}
}
