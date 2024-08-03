package wanted.pre_onboarding.jobposting.mapper;

import org.mapstruct.Mapper;
import wanted.pre_onboarding.company.entity.Company;
import wanted.pre_onboarding.jobposting.dto.JobPostingDetailsDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPostDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingsResponseDto;
import wanted.pre_onboarding.jobposting.entity.JobPosting;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JobPostingMapper {
    JobPosting postDtoToJobPosting(JobPostingPostDto postDto);

    default List<JobPostingsResponseDto> toJobPostingResponseDtos(List<JobPosting> postings) {
        return postings.stream()
                .map(this::toJobPostingResponseDto)
                .collect(Collectors.toList());
    }

    default JobPostingsResponseDto toJobPostingResponseDto(JobPosting posting) {
        Company company = posting.getCompany();

        return JobPostingsResponseDto.builder()
                .jobPostingId(posting.getId())
                .companyName(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .position(posting.getPosition())
                .reward(posting.getReward())
                .technology(posting.getTechnology())
                .build();
    }

    default JobPostingDetailsDto toJobPostingDetailsDto(Company company, JobPosting posting, List<Long> otherPostingIds) {
        return JobPostingDetailsDto.builder()
                .jobPostingId(posting.getId())
                .companyName(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .position(posting.getPosition())
                .reward(posting.getReward())
                .technology(posting.getTechnology())
                .description(posting.getDescription())
                .otherJobPostings(otherPostingIds)
                .build();
    }
}
