package roomdoor.dividendproject.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomdoor.dividendproject.service.FinancedService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("finance")
public class FinanceController {

	private final FinancedService financedService;

	@GetMapping("/dividend/{companyName}")
	public ResponseEntity<?> searchFinance(@PathVariable String companyName) {
		log.info("search dividend -> " + companyName);
		return ResponseEntity.ok(financedService.getDividendByCompanyName(companyName));
	}
}
