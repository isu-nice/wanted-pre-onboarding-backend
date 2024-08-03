package wanted.pre_onboarding.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JobPostingDetailsDto {
    private Long jobPostingId;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private Long reward;
    private String technology;
    private String description;
    private List<Long> otherJobPostings;
}
