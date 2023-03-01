package dev.ehyeon.ehyeon.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import dev.ehyeon.ehyeon.entity.User;
import dev.ehyeon.ehyeon.util.PasswordUtils;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void updatePassword() throws NoSuchAlgorithmException {
        // given
        User newUser = new User("49ehyeon42@gmail.com",
                PasswordUtils.hashPassword("HelloWorld"));

        // when
        User savedUser = userRepository.save(newUser);
        Optional<User> optionalUser = userRepository.findUserByEmail(savedUser.getEmail());

        if (optionalUser.isEmpty()) {
            fail();
        }

        User foundUser = optionalUser.get();

        foundUser.setPassword(PasswordUtils.hashPassword("Hello, world!"));

        // then
        assertThat(foundUser.getPassword()).isEqualTo(PasswordUtils.hashPassword("Hello, world!"));
    }
}
