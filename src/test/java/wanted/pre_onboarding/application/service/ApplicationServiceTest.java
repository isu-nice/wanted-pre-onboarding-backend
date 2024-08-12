package wanted.pre_onboarding.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wanted.pre_onboarding.application.dto.ApplicationDto;
import wanted.pre_onboarding.application.entity.Application;
import wanted.pre_onboarding.application.repository.ApplicationRepository;
import wanted.pre_onboarding.exception.BusinessLogicException;
import wanted.pre_onboarding.jobposting.entity.JobPosting;
import wanted.pre_onboarding.jobposting.service.JobPostingService;
import wanted.pre_onboarding.user.entity.User;
import wanted.pre_onboarding.user.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static wanted.pre_onboarding.exception.ExceptionCode.DUPLICATE_APPLICATION;

class ApplicationServiceTest {

    @Mock
    private ApplicationRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private JobPostingService jobPostingService;

    @InjectMocks
    private ApplicationService applicationService;

    private User user;
    private JobPosting jobPosting;
    private ApplicationDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John Doe");

        jobPosting = new JobPosting();
        jobPosting.setId(1L);

        dto = new ApplicationDto(2L, 5L);
    }

    @Test
    void applyToJobPosting_successfulApplication() {
        // given
        when(repository.existsByUserIdAndJobPostingId(dto.getUserId(), dto.getJobPostingId()))
                .thenReturn(false);
        when(userService.findUserById(dto.getUserId()))
                .thenReturn(user);
        when(jobPostingService.findJobPostingById(dto.getJobPostingId()))
                .thenReturn(jobPosting);

        // when & then
        assertDoesNotThrow(() -> applicationService.applyToJobPosting(dto));
        verify(repository, times(1)).save(any(Application.class));
    }

    @Test
    void applyToJobPosting_duplicateApplication() {
        // given
        when(repository.existsByUserIdAndJobPostingId(dto.getUserId(), dto.getJobPostingId()))
                .thenReturn(true);

        // when & then
        BusinessLogicException thrownException = assertThrows(BusinessLogicException.class,
                () -> applicationService.applyToJobPosting(dto));
        assertEquals(DUPLICATE_APPLICATION, thrownException.getExceptionCode());
        verify(repository, never()).save(any(Application.class));
    }
}
