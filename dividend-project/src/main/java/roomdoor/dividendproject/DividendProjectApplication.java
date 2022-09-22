package roomdoor.dividendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.ScrapedResult;
import roomdoor.dividendproject.scheduler.ScraperScheduler;
import roomdoor.dividendproject.scraper.FinanceScraper;
import roomdoor.dividendproject.scraper.YahooFinanceScraper;

@SpringBootApplication
@EnableScheduling
public class DividendProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DividendProjectApplication.class, args);
	}
}
