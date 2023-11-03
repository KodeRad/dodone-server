package com.dodone.dodone;

// The bean 'todoRepository', defined in com.dodone.dodone.TodoRepository defined in @EnableJpaRepositories
// declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration, could not be registered.
// A bean with that name has already been defined in file [/Users/konrad.krasocki/Documents/IntelliJ
// Projects/dodone/target/classes/com/dodone/dodone/Old/TodoRepository.class] and overriding is disabled.

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: CONTROLLER ADVICE how to handle global exceptions spring boot
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

        try {
            if (savedTodo != null && savedTodo.getId() != null) {
                return ResponseEntity
                        .status(HttpStatus
                                .CREATED).body(savedTodo);
            } else {
                return ResponseEntity
                        .status(HttpStatus
                                .INTERNAL_SERVER_ERROR).body(null);
            }

        } catch (Exception e) {
            String errorMessage = e.getMessage(); // Get the error message
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage); // Return the error message in the response body
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
