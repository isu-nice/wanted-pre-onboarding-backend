package wanted.pre_onboarding.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.pre_onboarding.exception.BusinessLogicException;
import wanted.pre_onboarding.user.entity.User;
import wanted.pre_onboarding.user.repository.UserRepository;

import static wanted.pre_onboarding.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findUserById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(USER_NOT_FOUND));
    }
}
