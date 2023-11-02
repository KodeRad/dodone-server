package com.dodone.dodone;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/todos")
public class TodoController {


    private final TodoService todoService;
    private final TodoRepository
            todoRepository;

    @GetMapping("")
    public List<Todo> getAll() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") Long id) {
        return todoService.getByID(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Todo>
    addTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.save(todo);

        if (savedTodo != null && savedTodo.getId() != null) {
            return ResponseEntity
                    .status(HttpStatus
                            .CREATED).body(todo);
        } else {
            return ResponseEntity
                    .status(HttpStatus
                            .INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo>
    update(@PathVariable("id") Long id,
           @RequestBody Todo updatedTodo) {
        // 1. We take the movie from our repository
        Todo todo = todoService.getByID(id);

        if (todo != null && todo.getId() != null) {
            // 2. We set the new values from a query
            todo.setName(updatedTodo.getName());
            todo.setRating(updatedTodo.getRating());
            todo.setPriority(updatedTodo.isPriority());
            todo.setDone(updatedTodo.isDone());
            // 3. We update todos
            todoService.save(todo);

            return ResponseEntity.status(HttpStatus.
                    CREATED).body(todo);
        } else {
            return ResponseEntity.status(HttpStatus.
                    INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo>
    partUpdate(@PathVariable("id") Long id,
               @RequestBody Todo updatedTodo) {

        Todo todo = todoService.getByID(id);

        if (todo != null && todo.getId() != null) {
            if (updatedTodo.getName() != null) {
                todo.setName(updatedTodo.getName());
            }

            if (updatedTodo.getRating() > 0) {
                todo.setRating(updatedTodo.getRating());
            }

            todo.setPriority(updatedTodo.isPriority());
            todo.setDone(updatedTodo.isDone());

            todoService.save(todo);

            return ResponseEntity.status(HttpStatus
                    .CREATED).body(todo);
        } else {
            return ResponseEntity.status(HttpStatus
                    .INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        todoService.delete(id);
        return 1;
    }
}
