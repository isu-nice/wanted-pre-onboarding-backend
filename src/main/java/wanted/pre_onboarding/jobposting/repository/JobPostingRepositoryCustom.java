package wanted.pre_onboarding.jobposting.repository;

import wanted.pre_onboarding.jobposting.entity.JobPosting;

import java.util.List;

public interface JobPostingRepositoryCustom {
    List<JobPosting> searchJobPostings(String query);
}
