package wanted.pre_onboarding.jobposting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class JobPostingsResponseDto {
    private Long jobPostingId;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private Long reward;
    private String technology;
}
