package roomdoor.dividendproject.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomdoor.dividendproject.entity.CompanyEntity;
import roomdoor.dividendproject.entity.DividendEntity;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.Dividend;
import roomdoor.dividendproject.model.ScrapedResult;
import roomdoor.dividendproject.repository.CompanyRepository;
import roomdoor.dividendproject.repository.DividendRepository;

@Service
@RequiredArgsConstructor
public class FinancedService {

	private final DividendRepository dividendRepository;

	private final CompanyRepository companyRepository;

	public ScrapedResult getDividendByCompanyName(String companyName) {
		CompanyEntity companyEntity = companyRepository.findByName(companyName)
			.orElseThrow(() -> new RuntimeException("not exist company name -> " + companyName));

		List<DividendEntity> dividendEntities = dividendRepository
			.findALlByCompanyId(companyEntity.getId());

		return ScrapedResult.builder()
			.company(Company.builder()
				.name(companyEntity.getName())
				.ticker(companyEntity.getTicker())
				.build())
			.dividendEntities(dividendEntities.stream()
				.map(e -> Dividend.builder()
					.date(e.getDate())
					.dividend(e.getDividend())
					.build())
				.collect(Collectors.toList()))
			.build();
	}

}