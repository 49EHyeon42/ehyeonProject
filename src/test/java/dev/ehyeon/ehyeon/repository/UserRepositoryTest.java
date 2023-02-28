package dev.ehyeon.ehyeon.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import dev.ehyeon.ehyeon.entity.User;
import java.time.LocalDateTime;
import java.util.List;
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
    public void save() {
        // given
        User newUser = new User("Test");

        // when
        User savedUser = userRepository.save(newUser);

        // then
        assertThat(savedUser.getName()).isEqualTo(newUser.getName());
    }

    @Test
    public void findById() {
        // given
        User newUser = new User("Test");

        // when
        User savedUser = userRepository.save(newUser);

        Optional<User> optionalUser = userRepository.findByIdAndDeletedAtIsNull(savedUser.getId());

        if (optionalUser.isEmpty()) {
            fail();
        }

        User foundUser = optionalUser.get();

        // then
        assertThat(foundUser.getName()).isEqualTo(savedUser.getName());
    }

    @Test
    public void findAll() {
        // given
        User newUser1 = new User("Test1");
        User newUser2 = new User("Test2");

        // when
        User savedUser1 = userRepository.save(newUser1);
        User savedUser2 = userRepository.save(newUser2);

        // then
        List<User> users = userRepository.findAllByDeletedAtIsNull();

        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0)).isEqualTo(savedUser1);
        assertThat(users.get(1)).isEqualTo(savedUser2);
    }

    @Test
    public void deleteById() {
        // given
        User newUser1 = new User("Test1");
        User newUser2 = new User("Test2");

        // when
        User savedUser1 = userRepository.save(newUser1);
        User savedUser2 = userRepository.save(newUser2);

        // then
        userRepository.softDeleteById(savedUser1.getId(), LocalDateTime.now());

        List<User> users = userRepository.findAllByDeletedAtIsNull();

        assertThat(users.size()).isEqualTo(1);

        Optional<User> optionalUser = userRepository.findByIdAndDeletedAtIsNull(savedUser1.getId());

        if (optionalUser.isPresent()) {
            fail();
        }
    }
}
