package wanted.pre_onboarding.jobposting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.jobposting.entity.JobPosting;

import java.util.List;


public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryCustom {

    List<JobPosting> findByCompanyId(Long companyId);
}
