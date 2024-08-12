package wanted.pre_onboarding.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.pre_onboarding.application.dto.ApplicationDto;
import wanted.pre_onboarding.application.service.ApplicationService;

@Tag(name = "Application", description = "지원 관련 API")
@RestController
@RequestMapping("/wanted/apply")
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "채용공고에 지원")
    @PostMapping
    public ResponseEntity applyJob(@RequestBody ApplicationDto request) {
        applicationService.applyToJobPosting(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
