package com.dodone.dodone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.LongToDoubleFunction;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("")
    public List<Todo> getAll() {
        return todoRepository.getAll();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") int id) {
        return todoRepository.getById(id);
    }

    @PostMapping("")
    public int addTodo(@RequestBody List<Todo> todos) {
        return todoRepository.saveTodo(todos);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Todo updatedTodo) {
        // 1. We take the movie from our repository
        Todo todo = todoRepository.getById(id);
        if (todo != null) {
            // 2. We set the new values from a query
            todo.setName(updatedTodo.getName());
            todo.setRating(updatedTodo.getRating());
            // 3. We update todos
            todoRepository.update(todo);

            return 1;
        } else {
            // TODO: Add error codes
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partUpdate(@PathVariable("id") int id, @RequestBody Todo updatedTodo) {
        Todo todo = todoRepository.getById(id);

        if (todo != null) {
            if (updatedTodo.getName() != null) {
                todo.setName(updatedTodo.getName());
            }

            if (updatedTodo.getRating() > 0) {
                todo.setRating(updatedTodo.getRating());
            }

            todoRepository.update(todo);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        todoRepository.delete(id);
        return 1;
    }
}
