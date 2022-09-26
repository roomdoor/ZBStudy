package roomdoor.dividendproject.exception.impl;

import org.springframework.http.HttpStatus;
import roomdoor.dividendproject.exception.AbstractException;

public class NotFoundIdException extends AbstractException {

	@Override
	public int getStatusCode() {
		return HttpStatus.NOT_FOUND.value();
	}

	@Override
	public String getMessage() {
		return "존재하지 않는 아이디입니다.";
	}
}
