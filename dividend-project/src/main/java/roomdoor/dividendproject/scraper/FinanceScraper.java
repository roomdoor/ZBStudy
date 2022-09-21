package roomdoor.dividendproject.scraper;

import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.ScrapedResult;

public interface FinanceScraper {

	public ScrapedResult scrap(Company company);

	public Company scrapCompanyByTicker(String ticker);

}
