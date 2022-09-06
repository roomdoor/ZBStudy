package zerobase.weather.config;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zerobase.weather.WeatherApplication;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Exception exceptionAllHandler() {
		logger.error("all exception");
		return new Exception();
	}
}
