package roomdoor.dividendproject.scraper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.Dividend;
import roomdoor.dividendproject.model.ScrapedResult;
import roomdoor.dividendproject.model.constants.Month;

@Slf4j
@Component
public class YahooFinanceScraper implements FinanceScraper{

	private static final String STATICS_URL = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&filter=div";
	private static final long START_TIME = 86400;
	private static final String SUMMERY_URL = "https://finance.yahoo.com/quote/%s";

	@Override
	public ScrapedResult scrap(Company company) {
		ScrapedResult scrapedResult = ScrapedResult.builder()
			.company(company)
			.build();

		try {
			long end = System.currentTimeMillis() / 1000;
			String url = String.format(STATICS_URL, company.getTicker(), START_TIME, end);
			Connection connection = Jsoup.connect(url);
			Document document = connection.get();

			Elements parsingDivs = document.getElementsByAttributeValue("data-test",
				"historical-prices");
			Element tableElement = parsingDivs.get(0);
			Element tbody = tableElement.child(1);
			List<Dividend> dividends = new ArrayList<>();

			for (Element e : tbody.children()) {
				String text = e.text();
				if (!text.endsWith("Dividend")) {
					continue;
				}

				String[] splits = text.split(" ");
				int month = Month.stringToIn(splits[0]);
				int day = Integer.parseInt(splits[1].replace(",", ""));
				int year = Integer.parseInt(splits[2]);
				String dividend = splits[3];

				if (month == -1) {
					throw new RuntimeException("Unexpected Month value -> " + splits[0]);
				}

				dividends.add(Dividend.builder()
					.date(LocalDateTime.of(year, month, day, 0, 0))
					.dividend(dividend)
					.build());
			}

			scrapedResult.setDividendEntities(dividends);

			log.info("scrap company -> " + company.getTicker() + " " + company.getName());
			return scrapedResult;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Company scrapCompanyByTicker(String ticker) {
		String url = String.format(SUMMERY_URL, ticker);
		Company result = Company.builder().ticker(ticker).build();
		try {
			Connection connect = Jsoup.connect(url);
			Document document = connect.get();
			Element titleElement = document.getElementsByClass("D(ib) Fz(18px)").get(0);
			String title = titleElement.text().split("\\(")[0].trim();
			if (title.isEmpty()) {
				throw new RuntimeException("title is empty");
			}

			result.setName(title);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}
}