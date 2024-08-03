package wanted.pre_onboarding.jobposting.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobPostingPatchDto {

    @Size(min = 2, max = 100)
    private String position;

    @Min(value = 10000, message = "채용 보상금은 10만원 이상이어야 합니다.")
    private Long reward;

    @Size(min = 10)
    private String description;

    private String technology;
}
