package wanted.pre_onboarding.jobposting.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.pre_onboarding.jobposting.dto.JobPostingDetailsDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPatchDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingPostDto;
import wanted.pre_onboarding.jobposting.dto.JobPostingsResponseDto;
import wanted.pre_onboarding.jobposting.service.JobPostingService;

import java.util.List;

@RestController
@RequestMapping("/job-postings")
@AllArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity registerJobPosting(@RequestBody JobPostingPostDto postDto) {
        jobPostingService.registerJobPosting(postDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateJobPosting(
            @PathVariable Long id,
            @RequestBody JobPostingPatchDto patchDto) {

        jobPostingService.updateJobPosting(id, patchDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping // 목록 조회
    public ResponseEntity getAllJobPostings() {
        List<JobPostingsResponseDto> response = jobPostingService.findAllJobPostings();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search") // 검색
    public ResponseEntity searchJobPostings(@RequestParam String query) {
        List<JobPostingsResponseDto> response = jobPostingService.searchJobPostingsByKeyword(query);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}") // 상세 페이지 조회
    public ResponseEntity getJobPosting(@PathVariable Long id) {
        JobPostingDetailsDto response = jobPostingService.findJobPostingDetails(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
