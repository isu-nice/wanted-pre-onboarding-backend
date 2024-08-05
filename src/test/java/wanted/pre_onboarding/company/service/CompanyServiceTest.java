package wanted.pre_onboarding.company.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wanted.pre_onboarding.company.entity.Company;
import wanted.pre_onboarding.company.repository.CompanyRepository;
import wanted.pre_onboarding.exception.BusinessLogicException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static wanted.pre_onboarding.exception.ExceptionCode.COMPANY_NOT_FOUND;


class CompanyServiceTest {

    @Mock
    private CompanyRepository repository;

    @InjectMocks
    private CompanyService companyService;

    private Company company;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        company = new Company();

    }

    @Test
    void findCompanyById_successfulFind() {
        // given
        Long companyId = 1L;
        when(repository.findById(companyId)).thenReturn(java.util.Optional.of(company));

        // when
        Company foundCompany = companyService.findCompanyById(companyId);

        // then
        assertEquals(company, foundCompany);
        verify(repository, times(1)).findById(companyId);
    }

    @Test
    void findCompanyById_companyNotFound() {
        // given
        Long companyId = 1L;
        when(repository.findById(companyId)).thenReturn(java.util.Optional.empty());

        // when & then
        BusinessLogicException thrownException = assertThrows(BusinessLogicException.class, () -> companyService.findCompanyById(companyId));
        assertEquals(COMPANY_NOT_FOUND, thrownException.getExceptionCode());
        verify(repository, times(1)).findById(companyId);
    }
}
