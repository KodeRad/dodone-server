package com.dodone.dodone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;


    private  TodoExampleRepository todoExampleRepository;

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
        todoExampleRepository.
        return todoRepository.saveManyTodo(todos);
    }

    @PostMapping("/single")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        int generatedID = todoRepository.saveTodo(todo);

        if(generatedID>0) {
            return ResponseEntity
                    .status(HttpStatus.CREATED).body(todo);
        } else {
            return  ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Todo updatedTodo) {
        // 1. We take the movie from our repository
        Todo todo = todoRepository.getById(id);
        if (todo != null) {
            // 2. We set the new values from a query
            todo.setName(updatedTodo.getName());
            todo.setRating(updatedTodo.getRating());
            todo.setPriority(updatedTodo.isPriority());
            todo.setDone(updatedTodo.isDone());
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

            // TODO: CHECK ITS BEHAVIOUR
            todo.setPriority(updatedTodo.isPriority());
            todo.setDone(updatedTodo.isDone());

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
