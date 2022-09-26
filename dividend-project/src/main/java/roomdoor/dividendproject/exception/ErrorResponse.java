package roomdoor.dividendproject.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ErrorResponse {

	private int statusCode;
	private String message;
}
