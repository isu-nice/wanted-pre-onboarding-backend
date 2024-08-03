package wanted.pre_onboarding.jobposting.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobPostingPostDto {
    @NotNull
    private Long companyId;

    @NotBlank(message = "채용 포지션을 입력해주세요")
    @Size(min = 2, max = 100)
    private String position;

    @NotNull(message = "채용 보상금을 입력해주세요")
    @Min(value = 10000, message = "채용 보상금은 10만원 이상이어야 합니다.")
    private Long reward;

    @NotBlank(message = "채용 내용을 입력해주세요")
    @Size(min = 10)
    private String description;

    @NotBlank(message = "사용기술을 입력해주세요")
    private String technology;
}
