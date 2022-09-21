package roomdoor.dividendproject.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import roomdoor.dividendproject.entity.CompanyEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

	private String ticker;
	private String name;

	public static Company entityToCompany(CompanyEntity companyEntity) {
		return Company.builder()
			.ticker(companyEntity.getTicker())
			.name(companyEntity.getName())
			.build();
	}

	public static List<Company> entityToCompany(List<CompanyEntity> listEntities) {
		List<Company> list = new ArrayList<>();

		for (var entity : listEntities) {
			list.add(entityToCompany(entity));
		}

		return list;
	}
}
