package wanted.pre_onboarding.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanted.pre_onboarding.application.entity.Application;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String technology;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
}
