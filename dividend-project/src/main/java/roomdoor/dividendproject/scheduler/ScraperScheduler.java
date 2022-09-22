package roomdoor.dividendproject.scheduler;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import roomdoor.dividendproject.entity.CompanyEntity;
import roomdoor.dividendproject.entity.DividendEntity;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.ScrapedResult;
import roomdoor.dividendproject.repository.CompanyRepository;
import roomdoor.dividendproject.repository.DividendRepository;
import roomdoor.dividendproject.scraper.FinanceScraper;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScraperScheduler {

	private final CompanyRepository companyRepository;
	private final DividendRepository dividendRepository;
	private final FinanceScraper yahooFinanceScraper;


	@Scheduled(cron = "${scheduler.scrap.yahoo}")
	public void yahooFinanceScheduling() {
		log.info("scraping scheduler is started");

		List<CompanyEntity> companies = companyRepository.findAll();

		for (var company : companies) {
			ScrapedResult scrapedResult = yahooFinanceScraper.scrap(Company.builder()
				.name(company.getName())
				.ticker(company.getTicker())
				.build());

			scrapedResult.getDividendEntities().stream()
				.map(e -> new DividendEntity(company.getId(), e))
				.forEach(e -> {
					boolean exists = dividendRepository.existsByCompanyIdAndDate
						(e.getCompanyId(), e.getDate());
					if (exists) {
						dividendRepository.save(e);
					}
				});

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
