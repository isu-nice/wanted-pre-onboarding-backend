package wanted.pre_onboarding.company.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_onboarding.company.entity.Company;
import wanted.pre_onboarding.company.repository.CompanyRepository;
import wanted.pre_onboarding.exception.BusinessLogicException;

import static wanted.pre_onboarding.exception.ExceptionCode.COMPANY_NOT_FOUND;

@Service
@Transactional
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public Company findCompanyById(Long companyId) {
        return repository.findById(companyId)
                .orElseThrow(() -> new BusinessLogicException(COMPANY_NOT_FOUND));
    }

}
