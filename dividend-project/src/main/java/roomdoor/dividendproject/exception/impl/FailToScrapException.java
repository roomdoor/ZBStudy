package roomdoor.dividendproject.exception.impl;

import org.springframework.http.HttpStatus;
import roomdoor.dividendproject.exception.AbstractException;

public class FailToScrapException extends AbstractException {

	@Override
	public int getStatusCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	@Override
	public String getMessage() {
		return "스크렙에 실패하였습니다.";
	}
}
