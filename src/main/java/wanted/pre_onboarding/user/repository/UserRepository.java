package wanted.pre_onboarding.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
