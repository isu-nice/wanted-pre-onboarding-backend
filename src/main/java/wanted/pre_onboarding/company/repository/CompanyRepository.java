package wanted.pre_onboarding.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
