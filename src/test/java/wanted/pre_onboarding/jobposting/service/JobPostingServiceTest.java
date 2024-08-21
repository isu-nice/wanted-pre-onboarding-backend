package wanted.pre_onboarding.jobposting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wanted.pre_onboarding.company.entity.Company;
import wanted.pre_onboarding.company.service.CompanyService;
import wanted.pre_onboarding.jobposting.dto.JobPostingDetailsDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPatchDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPostDto;
import wanted.pre_onboarding.jobposting.entity.JobPosting;
import wanted.pre_onboarding.jobposting.mapper.JobPostingMapper;
import wanted.pre_onboarding.jobposting.repository.JobPostingRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


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


}
