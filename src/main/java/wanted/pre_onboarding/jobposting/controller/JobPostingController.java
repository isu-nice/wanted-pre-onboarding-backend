package wanted.pre_onboarding.jobposting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "JobPosting", description = "채용공고 관련 API")
@RestController
@RequestMapping("/wanted/job-postings")
@AllArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @Operation(summary = "채용공고 등록")
    @PostMapping
    public ResponseEntity registerJobPosting(@RequestBody JobPostingPostDto postDto) {
        jobPostingService.registerJobPosting(postDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "채용공고 수정")
    @PatchMapping("/{id}")
    public ResponseEntity updateJobPosting(
            @PathVariable Long id,
            @RequestBody JobPostingPatchDto patchDto) {

        jobPostingService.updateJobPosting(id, patchDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "채용공고 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "전체 채용공고 목록 조회")
    @GetMapping
    public ResponseEntity getAllJobPostings() {
        List<JobPostingsResponseDto> response = jobPostingService.findAllJobPostings();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "채용공고 키워드 검색")
    @GetMapping("/search")
    public ResponseEntity searchJobPostings(@RequestParam String query) {
        List<JobPostingsResponseDto> response = jobPostingService.searchJobPostingsByKeyword(query);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "채용공고 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity getJobPosting(@PathVariable Long id) {
        JobPostingDetailsDto response = jobPostingService.findJobPostingDetails(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
