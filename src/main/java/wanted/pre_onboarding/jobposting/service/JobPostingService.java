package wanted.pre_onboarding.jobposting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_onboarding.company.entity.Company;
import wanted.pre_onboarding.company.service.CompanyService;
import wanted.pre_onboarding.exception.BusinessLogicException;
import wanted.pre_onboarding.jobposting.dto.JobPostingDetailsDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPatchDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPostDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingsResponseDto;
import wanted.pre_onboarding.jobposting.entity.JobPosting;
import wanted.pre_onboarding.jobposting.mapper.JobPostingMapper;
import wanted.pre_onboarding.jobposting.repository.JobPostingRepository;

import java.util.List;
import java.util.Optional;

import static wanted.pre_onboarding.exception.ExceptionCode.JOB_POSTING_NOT_FOUND;
import static wanted.pre_onboarding.exception.ExceptionCode.REWARD_TOO_LOW;

@Service
@Transactional
@AllArgsConstructor
public class JobPostingService {

    private static final long MINIMUM_REWARD = 100_000;

    private final JobPostingRepository repository;
    private final JobPostingMapper jobPostingMapper;
    private final CompanyService companyService;

    // 채용 공고 등록
    @Transactional(propagation = Propagation.REQUIRED)
    public JobPosting registerJobPosting(JobPostingPostDto postDto) {
        Company company = companyService.findCompanyById(postDto.getCompanyId());

        JobPosting jobPosting = jobPostingMapper.postDtoToJobPosting(postDto);
        jobPosting.setCompany(company);

        return repository.save(jobPosting);
    }

    /**
     * 채용 공고 수정
     *
     * @param //업데이트할 채용 공고 객체
     * @param id
     * @throws BusinessLogicException 해당 채용 공고가 존재하지 않을 때
     * @throws BusinessLogicException 보상금이 10만 원 미만일 때
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void updateJobPosting(Long id, JobPostingPatchDto patchDto) {
        JobPosting existingjobPosting = findJobPostingById(id);
        updateJobPostingFields(patchDto, existingjobPosting);

        repository.save(existingjobPosting);
    }

    // 채용 공고 삭제
    public void deleteJobPosting(Long id) {
        JobPosting existingjobPosting = findJobPostingById(id);

        repository.delete(existingjobPosting);
    }

    // 모든 채용공고 조회
    @Transactional(readOnly = true)
    public List<JobPostingsResponseDto> findAllJobPostings() {
        List<JobPosting> allJobPostings = repository.findAll();
        return jobPostingMapper.toJobPostingResponseDtos(allJobPostings);
    }

    // 채용공고 키워드 검색
    @Transactional(readOnly = true)
    public List<JobPostingsResponseDto> searchJobPostingsByKeyword(String query) {
        List<JobPosting> allJobPostings = repository.searchJobPostings(query);

        return jobPostingMapper.toJobPostingResponseDtos(allJobPostings);
    }

    // 채용 상세 페이지 조회
    @Transactional(readOnly = true)
    public JobPostingDetailsDto findJobPostingDetails(Long id) {
        JobPosting jobPosting = findJobPostingById(id);
        Company company = jobPosting.getCompany();
        List<JobPosting> otherPostings = repository.findByCompanyId(company.getId());

        List<Long> otherPostingIds = otherPostings.stream()
                .map(JobPosting::getId)
                .filter(postingId -> !postingId.equals(id))
                .toList();

        return jobPostingMapper.toJobPostingDetailsDto(company, jobPosting, otherPostingIds);
    }

    private void updateJobPostingFields(JobPostingPatchDto patchDto, JobPosting existingjobPosting) {
        Optional.ofNullable(patchDto.getPosition())
                .ifPresent(existingjobPosting::setPosition);

        Optional.ofNullable(patchDto.getReward())
                .ifPresent(reward -> {
                    validateReward(reward);
                    existingjobPosting.setReward(reward);
                });

        Optional.ofNullable(patchDto.getDescription())
                .ifPresent(existingjobPosting::setPosition);

        Optional.ofNullable(patchDto.getTechnology())
                .ifPresent(existingjobPosting::setTechnology);
    }

    public JobPosting findJobPostingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(JOB_POSTING_NOT_FOUND));
    }

    /**
     * 보상금 검증
     *
     * @param reward 보상금
     * @throws BusinessLogicException 보상금이 10만 원 미만일 때
     */
    public void validateReward(Long reward) {
        if (reward < MINIMUM_REWARD) {
            throw new BusinessLogicException(REWARD_TOO_LOW);
        }
    }
}
