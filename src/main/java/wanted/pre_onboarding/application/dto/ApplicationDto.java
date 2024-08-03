package wanted.pre_onboarding.application.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long jobPostingId;
}
