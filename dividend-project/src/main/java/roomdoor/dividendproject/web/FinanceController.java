package roomdoor.dividendproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomdoor.dividendproject.service.FinancedService;

@RestController
@RequiredArgsConstructor
@RequestMapping("finance")
public class FinanceController {

	private final FinancedService financedService;

	@GetMapping("/dividend/{companyName}")
	public ResponseEntity<?> searchFinance(@PathVariable String companyName) {
		return ResponseEntity.ok(financedService.getDividendByCompanyName(companyName));
	}
}
