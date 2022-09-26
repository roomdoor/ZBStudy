package roomdoor.dividendproject.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import roomdoor.dividendproject.entity.CompanyEntity;
import roomdoor.dividendproject.entity.DividendEntity;
import roomdoor.dividendproject.exception.impl.NoCompanyException;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.Dividend;
import roomdoor.dividendproject.model.ScrapedResult;
import roomdoor.dividendproject.model.constants.CacheKey;
import roomdoor.dividendproject.repository.CompanyRepository;
import roomdoor.dividendproject.repository.DividendRepository;

@Service
@RequiredArgsConstructor
public class FinancedService {

	private final DividendRepository dividendRepository;

	private final CompanyRepository companyRepository;

	@Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
	public ScrapedResult getDividendByCompanyName(String companyName) {
		CompanyEntity companyEntity = companyRepository.findByName(companyName)
			.orElseThrow(NoCompanyException::new);

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
