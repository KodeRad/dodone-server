package com.dodone.dodone.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.dodone.dodone.service.TodoService;
import org.springframework.http.HttpStatus;
import com.dodone.dodone.entity.Todo;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;


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
                            .CREATED).body(savedTodo);
        } else {
            return ResponseEntity
                    .status(HttpStatus
                            .INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo>
    update(@PathVariable("id") Long id,
           @RequestBody Todo updatedTodo) {
        Todo todo = todoService.getByID(id);

        if (todo != null ) {
            todo.setName(updatedTodo.getName());
            todo.setCreatedDate(updatedTodo.getCreatedDate());
            todo.setDueDate(updatedTodo.getDueDate());
            todo.setPriority(updatedTodo.isPriority());
            todo.setDone(updatedTodo.isDone());

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

            if (updatedTodo.getDueDate() != null) {
                todo.setDueDate(updatedTodo.getDueDate());
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
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
