package wanted.pre_onboarding.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.application.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByUserIdAndJobPostingId(Long userId, Long jobPosting);
}
