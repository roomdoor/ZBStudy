package roomdoor.dividendproject.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import roomdoor.dividendproject.entity.CompanyEntity;
import roomdoor.dividendproject.entity.DividendEntity;
import roomdoor.dividendproject.exception.impl.AlreadyExistCompanyException;
import roomdoor.dividendproject.exception.impl.FailToScrapException;
import roomdoor.dividendproject.exception.impl.NoCompanyException;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.ScrapedResult;
import roomdoor.dividendproject.repository.CompanyRepository;
import roomdoor.dividendproject.repository.DividendRepository;
import roomdoor.dividendproject.scraper.FinanceScraper;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final FinanceScraper yahooFinanceScraper;
	private final CompanyRepository companyRepository;
	private final DividendRepository dividendRepository;

	private final Trie trie;


	public Company save(String ticker) {
		boolean exist = companyRepository.existsByTicker(ticker);

		if (exist) {
			throw new AlreadyExistCompanyException();
		}

		return storeCompanyAndDividend(ticker);
	}

	private Company storeCompanyAndDividend(String ticker) {
		Company company = yahooFinanceScraper.scrapCompanyByTicker(ticker);

		if (ObjectUtils.isEmpty(company)) {
			throw new FailToScrapException();
		}

		ScrapedResult scrapedResult = yahooFinanceScraper.scrap(company);

		CompanyEntity companyEntity = companyRepository.save(new CompanyEntity(company));

		List<DividendEntity> dividendEntities = scrapedResult.getDividendEntities().stream()
			.map(e -> new DividendEntity(companyEntity.getId(), e)).collect(Collectors.toList());

		dividendRepository.saveAll(dividendEntities);

		return company;
	}

	public Page<CompanyEntity> list(Pageable pageable) {

		return companyRepository.findAll(pageable);
	}

	public void addAutoCompleteKeyword(String keyword) {
		trie.put(keyword, null);
	}

	public List<String> autoComplete(String keyword) {
		return (List<String>) trie.prefixMap(keyword).keySet().stream()
			.collect(Collectors.toList());
	}

	public void deleteAutoCompleteKeyword(String keyword) {
		trie.remove(keyword);
	}

	public List<String> getCompanyNamesByKeyword(String keyword) {
		Pageable limit = PageRequest.of(0, 10);
		Page<CompanyEntity> companyEntities = companyRepository.findByNameStartingWithIgnoreCase(
			keyword, limit);

		return companyEntities.stream().map(CompanyEntity::getName)
			.collect(Collectors.toList());
	}

	public String deleteCompany(String ticker) {
		CompanyEntity company = companyRepository.findByTicker(ticker)
			.orElseThrow(NoCompanyException::new);

		dividendRepository.deleteAllByCompanyId(company.getId());
		companyRepository.delete(company);
		deleteAutoCompleteKeyword(company.getName());

		return company.getName();
	}
}
