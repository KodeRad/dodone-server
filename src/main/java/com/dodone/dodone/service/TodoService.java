package com.dodone.dodone.service;

import com.dodone.dodone.controller.errors.ExceptionNoSuchElement;
import com.dodone.dodone.entity.Todo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.dodone.dodone.repository.TodoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {

    private final TodoRepository
            todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
    public Todo getByID(Long id) {
        return todoRepository.findById(id).
                orElseThrow(ExceptionNoSuchElement::new);
    }
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);;
    }

}
