package zerobase.weather.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.Error.InvalidDateException;
import zerobase.weather.WeatherApplication;
import zerobase.weather.domain.DateWeather;
import zerobase.weather.domain.Diary;
import zerobase.weather.repository.JpaDateWeatherRepository;
import zerobase.weather.repository.JpaDiaryRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

	private final JpaDiaryRepository diaryRepository;
	private final JpaDateWeatherRepository dateWeatherRepository;
	private final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

	@Value("${openWeatherMapAPI.key}")
	private String apiKey;

	public void createDiary(LocalDate date, String text) {
		logger.info("started to created diary");

		DateWeather dateWeather = getDateWeather(date);

		// 가져온 데이터 db에 넣기
		Diary diary = new Diary();
		diary.setDateWeather(dateWeather);
		diary.setText(text);
		diary.setDate(date);
		diaryRepository.save(diary);
		logger.info("end to created diary");
	}

	private DateWeather getDateWeather(LocalDate date) {
		Optional<DateWeather> dateWeatherFromDb = dateWeatherRepository.findByDate(date);

		return dateWeatherFromDb.orElseGet(this::getWeatherFromApi);
	}

	private String getWeatherString() {
		String apiURL = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;
		try {
			URL url = new URL(apiURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}

			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			return response.toString();
		} catch (Exception e) {
			return "failed to get response";
		}
	}

	private Map<String, Object> parseWeather(String jsonString) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;

		try {
			jsonObject = (JSONObject) jsonParser.parse(jsonString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		Map<String, Object> resultMap = new HashMap<>();

		JSONArray weatherDataArray = (JSONArray) jsonObject.get("weather");
		JSONObject weatherData = (JSONObject) weatherDataArray.get(0);
		JSONObject mainData = (JSONObject) jsonObject.get("main");
		resultMap.put("temp", mainData.get("temp"));
		resultMap.put("main", weatherData.get("main"));
		resultMap.put("icon", weatherData.get("icon"));

		return resultMap;
	}

	@Transactional(readOnly = true)
	public List<Diary> readDiary(LocalDate date) {
		if (date.isAfter(LocalDate.ofYearDay(3050, 1))) {
			throw new InvalidDateException();
		}

		logger.debug("read diary");

		return diaryRepository.findAllByDate(date);
	}

	public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
		return diaryRepository.findAllByDateBetween(startDate, endDate);
	}

	public void updateDiary(LocalDate date, String text) {
		Diary diary = diaryRepository.getFirstByDate(date);
		diary.setText(text);
		diaryRepository.save(diary);
	}

	@Transactional
	public void deleteDiary(LocalDate date) {
		diaryRepository.deleteAllByDate(date);
	}


	@Transactional
	@Scheduled(cron = "0 0 1 * * *")
	public void saveWeatherDate() {
		dateWeatherRepository.save(getWeatherFromApi());
	}

	private DateWeather getWeatherFromApi() {
		// api 에서 데이터 가져오기
		String weatherData = getWeatherString();

		// json 데이터에서 필요한 데이터만 가져오기
		Map<String, Object> parsedWeather = parseWeather(weatherData);

		return DateWeather.builder()
			.date(LocalDate.now())
			.weather(parsedWeather.get("main").toString())
			.icon(parsedWeather.get("icon").toString())
			.temperature((Double) parsedWeather.get("temp"))
			.build();
	}
}
