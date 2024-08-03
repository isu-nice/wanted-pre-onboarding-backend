package wanted.pre_onboarding.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.pre_onboarding.application.dto.ApplicationDto;
import wanted.pre_onboarding.application.service.ApplicationService;

@RestController
@RequestMapping("/apply")
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity applyJob(@RequestBody ApplicationDto request) {
        applicationService.applyToJobPosting(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
