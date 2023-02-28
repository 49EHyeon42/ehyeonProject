package dev.ehyeon.ehyeon;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TodoRepositoryIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void givenTodo_whenSave_thenGetOk() {
        // given
        Todo todo = new Todo(1, "Test");

        // when
        Todo savedTodo = todoRepository.save(todo);

        // then
        assertThat(savedTodo.getId()).isNotNull();
        assertThat(savedTodo.getTitle()).isEqualTo(todo.getTitle());
        assertThat(savedTodo.isCompleted()).isEqualTo(todo.isCompleted());
    }
}
