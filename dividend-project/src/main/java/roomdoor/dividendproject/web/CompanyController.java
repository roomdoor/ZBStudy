package roomdoor.dividendproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roomdoor.dividendproject.entity.CompanyEntity;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;

	@GetMapping("/autocomplete")
	public ResponseEntity<?> autoComplete(@RequestParam String keyword) {
		return null;
	}

	@GetMapping("")
	public ResponseEntity<?> searchCompany(final Pageable pageable) {
		return ResponseEntity.ok(companyService.list(pageable));
	}

	@PostMapping("")
	public ResponseEntity<?> addCompany(@RequestBody Company request) {

		String ticker = request.getTicker().trim();
		if (ObjectUtils.isEmpty(ticker)) {
			throw new RuntimeException("ticker is empty");
		}

		Company save = companyService.save(ticker);

		return ResponseEntity.ok(save);
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteCompany() {
		return null;
	}
}
