package wanted.pre_onboarding.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wanted.pre_onboarding.exception.BusinessLogicException;
import wanted.pre_onboarding.user.entity.User;
import wanted.pre_onboarding.user.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("홍길동");
        user.setTechnology("Java");
    }

    @Test
    void findUserById_userExists() {
        // given
        Long userId = 1L;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        // when
        User foundUser = userService.findUserById(userId);

        // then
        assertEquals(user, foundUser);
    }

    @Test
    void findUserById_userNotFound() {
        // given
        Long userId = 2L;
        when(repository.findById(userId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(BusinessLogicException.class, () -> userService.findUserById(userId));
    }
}
