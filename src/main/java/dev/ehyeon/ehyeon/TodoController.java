package dev.ehyeon.ehyeon;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        System.out.println(todo.getTitle());
        System.out.println(todo.getDescription());
        return todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    @GetMapping("/all")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @PostMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
