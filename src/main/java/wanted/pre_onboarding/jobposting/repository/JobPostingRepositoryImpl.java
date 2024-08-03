package wanted.pre_onboarding.jobposting.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wanted.pre_onboarding.company.entity.QCompany;
import wanted.pre_onboarding.jobposting.entity.JobPosting;
import wanted.pre_onboarding.jobposting.entity.QJobPosting;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {

    private final JPAQueryFactory factory;

    @Override
    public List<JobPosting> searchJobPostings(String query) {
        QJobPosting posting = QJobPosting.jobPosting;
        QCompany company = QCompany.company;

        BooleanBuilder builder = createSearchBuilder(query, posting, company);

        return factory.selectFrom(posting)
                .leftJoin(posting.company, company)
                .where(builder)
                .fetch();
    }

    private BooleanBuilder createSearchBuilder(String query, QJobPosting posting, QCompany company) {
        BooleanBuilder builder = new BooleanBuilder();
        String searchLower = query.toLowerCase();

        return builder.and(posting.position.toLowerCase().contains(searchLower))
                .or(posting.technology.toLowerCase().contains(searchLower))
                .or(company.name.toLowerCase().contains(searchLower));
    }
}
