package wanted.pre_onboarding.application.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import wanted.pre_onboarding.jobposting.entity.JobPosting;
import wanted.pre_onboarding.user.entity.User;

@Entity
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_POSTING_ID", nullable = false)
    private JobPosting jobPosting;

    public Application(User user, JobPosting jobPosting, String status) {
        this.user = user;
        this.jobPosting = jobPosting;
        this.status = status;
    }
}
