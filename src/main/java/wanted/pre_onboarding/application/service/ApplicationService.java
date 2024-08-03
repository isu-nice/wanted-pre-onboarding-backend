package wanted.pre_onboarding.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.pre_onboarding.application.dto.ApplicationDto;
import wanted.pre_onboarding.application.entity.Application;
import wanted.pre_onboarding.application.repository.ApplicationRepository;
import wanted.pre_onboarding.exception.BusinessLogicException;
import wanted.pre_onboarding.jobposting.entity.JobPosting;
import wanted.pre_onboarding.jobposting.service.JobPostingService;
import wanted.pre_onboarding.user.entity.User;
import wanted.pre_onboarding.user.service.UserService;

import static wanted.pre_onboarding.exception.ExceptionCode.DUPLICATE_APPLICATION;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final UserService userService;
    private final JobPostingService jobPostingService;

    public void applyToJobPosting(ApplicationDto dto) {
        Long userId = dto.getUserId();
        Long jobPostingId = dto.getJobPostingId();

        // 중복 지원 검증
        validateDuplicateApplication(userId, jobPostingId);

        User user = userService.findUserById(userId);
        JobPosting jobPosting = jobPostingService.findJobPostingById(jobPostingId);

        Application application = createApplication(user, jobPosting);

        repository.save(application);
    }

    private Application createApplication(User user, JobPosting jobPosting) {
        return new Application(user, jobPosting, "지원서 제출");
    }

    private void validateDuplicateApplication(Long userId, Long jobPostingId) {
        if (repository.existsByUserIdAndJobPostingId(userId, jobPostingId)) {
            throw new BusinessLogicException(DUPLICATE_APPLICATION);
        }
    }
}
