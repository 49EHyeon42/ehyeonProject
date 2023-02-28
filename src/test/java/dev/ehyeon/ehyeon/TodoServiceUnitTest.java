package dev.ehyeon.ehyeon;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TodoServiceUnitTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        todoService = new TodoService(todoRepository);
    }

    @Test
    public void givenTodo_whenCreateTodo_thenGetTodo() {
        // given
        Todo todo = new Todo(1, "Test");

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        // when
        Todo createdTodo = todoService.createTodo(todo);

        // then
        assertThat(createdTodo.getId()).isEqualTo(1L);
        assertThat(createdTodo.getTitle()).isEqualTo(todo.getTitle());
        assertThat(createdTodo.isCompleted()).isEqualTo(todo.isCompleted());
    }

    @Test
    public void givenTodoId_whenGetTodoById_thenGetTodo() {
        // given
        long id = 1;

        Todo todo = new Todo(id, "Test");

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        // when
        Todo retrievedTodo = todoService.getTodoById(id);

        // then
        assertThat(retrievedTodo.getId()).isEqualTo(todo.getId());
        assertThat(retrievedTodo.getTitle()).isEqualTo(todo.getTitle());
        assertThat(retrievedTodo.isCompleted()).isEqualTo(todo.isCompleted());
    }

    @Test
    public void givenTodos_whenGetAllTodos_thenGetTodos() {
        // given
        List<Todo> todos = new ArrayList<>();

        Todo todo1 = new Todo(1, "Test1");
        Todo todo2 = new Todo(2, "Test2");

        todos.add(todo1);
        todos.add(todo2);

        when(todoRepository.findAll()).thenReturn(todos);

        // when
        List<Todo> retrievedTodos = todoService.getAllTodos();

        // then
        assertThat(retrievedTodos.size()).isEqualTo(2);
        assertThat(retrievedTodos.get(0)).isEqualTo(todo1);
        assertThat(retrievedTodos.get(1)).isEqualTo(todo2);
    }

    @Test
    public void givenTodo_whenUpdateTodo_thenGetTodo() {
        // given
        Todo todo = new Todo(1, "Test");

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        // when
        Todo updatedTodo = todoService.updateTodoById(todo.getId(), todo);

        // then
        assertThat(updatedTodo.getId()).isEqualTo(todo.getId());
        assertThat(updatedTodo.getTitle()).isEqualTo(todo.getTitle());
        assertThat(updatedTodo.isCompleted()).isEqualTo(todo.isCompleted());
    }
}
