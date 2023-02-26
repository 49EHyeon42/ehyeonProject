package dev.ehyeon.ehyeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {

    private static final Map<Long, Todo> store = new HashMap<>();
    private static long sequence = 0;

    public Todo save(Todo todo) {
        todo.setId(sequence++);
        store.put(todo.getId(), todo);
        return todo;
    }

    public Optional<Todo> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(long id) {
        store.remove(id);
    }
}
