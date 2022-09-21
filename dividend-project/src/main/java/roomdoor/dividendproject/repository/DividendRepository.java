package roomdoor.dividendproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roomdoor.dividendproject.entity.DividendEntity;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {

	List<DividendEntity> findALlByCompanyId(Long companyId);

}
