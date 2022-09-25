package roomdoor.dividendproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roomdoor.dividendproject.model.Company;
import roomdoor.dividendproject.model.constants.CacheKey;
import roomdoor.dividendproject.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;

	private final CacheManager redisCacheManager;

	@GetMapping("/autocomplete")
	public ResponseEntity<?> autoComplete(@RequestParam String keyword) {
//		var result = companyService.autoComplete(keyword);
		var result = companyService.getCompanyNamesByKeyword(keyword);

		return ResponseEntity.ok(result);
	}

	@GetMapping("")
	@PreAuthorize("hasRole('READ')")
	public ResponseEntity<?> searchCompany(final Pageable pageable) {
		return ResponseEntity.ok(companyService.list(pageable));
	}

	@PostMapping("")
	@PreAuthorize("hasRole('WRITE')")
	public ResponseEntity<?> addCompany(@RequestBody Company request) {

		String ticker = request.getTicker().trim();
		if (ObjectUtils.isEmpty(ticker)) {
			throw new RuntimeException("ticker is empty");
		}

		Company save = companyService.save(ticker);
		companyService.addAutoCompleteKeyword(save.getName());

		return ResponseEntity.ok(save);
	}

	@DeleteMapping("/{ticker}")
	@PreAuthorize("hasRole('WRITE')")
	public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {
		String companyName = companyService.deleteCompany(ticker);
		clearFinanceCache(companyName);
		return ResponseEntity.ok(companyName);
	}

	public void clearFinanceCache(String companyName) {
		redisCacheManager.getCache(CacheKey.KEY_FINANCE).evict(companyName);
	}
}
