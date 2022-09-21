package roomdoor.dividendproject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roomdoor.dividendproject.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
	boolean existsByTicker(String ticker);

	Optional<CompanyEntity> findByName(String name);
}
