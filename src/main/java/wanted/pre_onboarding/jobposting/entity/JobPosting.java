package wanted.pre_onboarding.jobposting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanted.pre_onboarding.application.entity.Application;
import wanted.pre_onboarding.company.entity.Company;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter @Setter
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position; //채용포지션

    @Column(nullable = false)
    private Long reward; //채용보상금

    @Column(nullable = false)
    private String description; //채용내용

    @Column(nullable = false)
    private String technology; //사용기술

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
}
