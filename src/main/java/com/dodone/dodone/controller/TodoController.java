package com.dodone.dodone.controller;

import com.dodone.dodone.entity.Todo;
import com.dodone.dodone.service.EmailService;
import com.dodone.dodone.service.TodoEmailScheduler;
import com.dodone.dodone.service.TodoService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final EmailService emailService;
    private final TodoEmailScheduler emailScheduler;
    private final int sec = 1000;

    //TODO: CHANGE IT TO MINUTES AND IMPLEMENT FLAGS (ASK GOSIA)
    //TODO: IT SHOULD GO SOMEWHERE ELSE (OTHER SERVICE) in other process
    @Scheduled(fixedDelay = sec)
    public void sendEmailSpecificTime() throws MessagingException {
        emailScheduler.sendEmail();
    }


    @GetMapping("")
    public List<Todo> getAll() throws MessagingException {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") Long id) {
        return todoService.getByID(id);
    }

    // POST ADD
    @PostMapping("/add")
    public ResponseEntity<Todo>
    addTodo(@RequestBody Todo todo) {
        System.out.println(todo);
        Todo savedTodo = todoService.save(todo);
        System.out.println(savedTodo);


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

    // PUT REPLACE
    @PutMapping("/{id}")
    public ResponseEntity<Todo>
    update(@PathVariable("id") Long id,
           @RequestBody Todo updatedTodo) {
        // 1. We take the movie from our repository
        Todo todo = todoService.getByID(id);


        // TODO: MOVE TO LOGIC TO THE CONTROLLER
        if (todo != null && todo.getId() != null) {
            // 2. We set the new values from a query
            todo.setName(updatedTodo.getName());
            todo.setCreatedDate(updatedTodo.getCreatedDate());
            todo.setDueDate(updatedTodo.getDueDate());
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


    // PATCH
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

            if (updatedTodo.getCreatedDate() != null) {
                todo.setCreatedDate(updatedTodo.getCreatedDate());
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
