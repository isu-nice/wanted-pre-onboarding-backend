package wanted.pre_onboarding.jobposting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static wanted.pre_onboarding.exception.ExceptionCode.JOB_POSTING_NOT_FOUND;
import static wanted.pre_onboarding.exception.ExceptionCode.REWARD_TOO_LOW;


class JobPostingServiceTest {

    @Mock
    private JobPostingRepository repository;

    @Mock
    private JobPostingMapper jobPostingMapper;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private JobPostingService jobPostingService;

    private JobPosting jobPosting;
    private Company company;
    private JobPostingPostDto postDto;
    private JobPostingPatchDto patchDto;
    private JobPostingDetailsDto detailsDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        company = new Company();

        jobPosting = new JobPosting();
        jobPosting.setId(1L);
        jobPosting.setPosition("Software Engineer");
        jobPosting.setReward(150_000L);
        jobPosting.setDescription("A great job posting");
        jobPosting.setTechnology("Java");
        jobPosting.setCompany(company);

        postDto = new JobPostingPostDto(
                2L,
                "백엔드 개발자",
                200_000L,
                "지원하세요",
                "Java"
        );

        patchDto = new JobPostingPatchDto(
                "개발자",
                500_000L,
                "지원지원",
                "React"
        );

        detailsDto = new JobPostingDetailsDto(
                12L,
                "회사이름",
                "한국",
                "부산",
                "디자이너",
                900000L,
                "Kotlin",
                "여기로지원",
                List.of(2L, 5L, 16L)
        );
    }

    @Test
    void registerJobPosting_successfulRegistration() {
        // given
        when(companyService.findCompanyById(postDto.getCompanyId())).thenReturn(company);
        when(jobPostingMapper.postDtoToJobPosting(postDto)).thenReturn(jobPosting);
        when(repository.save(jobPosting)).thenReturn(jobPosting);

        // when
        JobPosting result = jobPostingService.registerJobPosting(postDto);

        // then
        assertEquals(jobPosting, result);
        verify(companyService, times(1)).findCompanyById(postDto.getCompanyId());
        verify(jobPostingMapper, times(1)).postDtoToJobPosting(postDto);
        verify(repository, times(1)).save(jobPosting);
    }

    @Test
    void updateJobPosting_successfulUpdate() {
        // given
        when(repository.findById(jobPosting.getId())).thenReturn(Optional.of(jobPosting));
        when(repository.save(jobPosting)).thenReturn(jobPosting);

        // when
        jobPostingService.updateJobPosting(jobPosting.getId(), patchDto);

        // then
        assertEquals(patchDto.getPosition(), jobPosting.getPosition());
        assertEquals(patchDto.getReward(), jobPosting.getReward());
        verify(repository, times(1)).findById(jobPosting.getId());
        verify(repository, times(1)).save(jobPosting);
    }

    @Test
    void updateJobPosting_jobPostingNotFound() {
        // given
        when(repository.findById(jobPosting.getId())).thenReturn(Optional.empty());

        // when & then
        BusinessLogicException thrownException = assertThrows(BusinessLogicException.class, () -> jobPostingService.updateJobPosting(jobPosting.getId(), patchDto));
        assertEquals(JOB_POSTING_NOT_FOUND, thrownException.getExceptionCode());
        verify(repository, times(1)).findById(jobPosting.getId());
        verify(repository, never()).save(any(JobPosting.class));
    }


    @Test
    void deleteJobPosting_successfulDeletion() {
        // given
        when(repository.findById(jobPosting.getId())).thenReturn(Optional.of(jobPosting));

        // when
        jobPostingService.deleteJobPosting(jobPosting.getId());

        // then
        verify(repository, times(1)).findById(jobPosting.getId());
        verify(repository, times(1)).delete(jobPosting);
    }

    @Test
    void deleteJobPosting_jobPostingNotFound() {
        // given
        when(repository.findById(jobPosting.getId())).thenReturn(Optional.empty());

        // when & then
        BusinessLogicException thrownException = assertThrows(BusinessLogicException.class, () -> jobPostingService.deleteJobPosting(jobPosting.getId()));
        assertEquals(JOB_POSTING_NOT_FOUND, thrownException.getExceptionCode());
        verify(repository, times(1)).findById(jobPosting.getId());
        verify(repository, never()).delete(any(JobPosting.class));
    }

    @Test
    void findAllJobPostings_successfulFindAll() {
        // given
        List<JobPosting> jobPostings = List.of(jobPosting);

        // when
        when(repository.findAll()).thenReturn(jobPostings);

        // when
        List<JobPostingsResponseDto> result = jobPostingService.findAllJobPostings();

        // then
        verify(repository, times(1)).findAll();
    }


    @Test
    void searchJobPostingsByKeyword_successfulSearch() {
        // given
        List<JobPosting> jobPostings = List.of(jobPosting);

        // when
        when(repository.searchJobPostings("Java")).thenReturn(jobPostings);

        // when
        List<JobPostingsResponseDto> result = jobPostingService.searchJobPostingsByKeyword("Java");

        // then
        assertEquals(jobPostings, result);
        verify(repository, times(1)).searchJobPostings("Java");
    }


    @Test
    void findJobPostingDetails_successfulFind() {
        // given
        List<JobPosting> otherJobPostings = List.of(jobPosting);
        List<Long> otherPostingIds = List.of(jobPosting.getId());
        when(repository.findById(jobPosting.getId())).thenReturn(Optional.of(jobPosting));
        when(repository.findByCompanyId(company.getId())).thenReturn(otherJobPostings);
        when(jobPostingMapper.toJobPostingDetailsDto(company, jobPosting, otherPostingIds)).thenReturn(detailsDto);

        // when
        JobPostingDetailsDto result = jobPostingService.findJobPostingDetails(jobPosting.getId());

        // then
        assertEquals(detailsDto, result);
        verify(repository, times(1)).findById(jobPosting.getId());
        verify(repository, times(1)).findByCompanyId(company.getId());
        verify(jobPostingMapper, times(1)).toJobPostingDetailsDto(company, jobPosting, otherPostingIds);
    }



}
