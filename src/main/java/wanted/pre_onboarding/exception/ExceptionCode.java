package wanted.pre_onboarding.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    // JobPosting
    JOB_POSTING_NOT_FOUND(404, "해당 채용공고가 존재하지 않습니다"),
    REWARD_TOO_LOW(400, "채용보상금은 10만원 이상이어야 합니다"),

    // Company
    COMPANY_NOT_FOUND(404, "해당 회사가 존재하지 않습니다"),

    // User
    USER_NOT_FOUND(404, "해당 회원이 존재하지 않습니다"),

    // Application
    DUPLICATE_APPLICATION(400, "이미 지원한 채용공고입니다");

    private int status;

    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
