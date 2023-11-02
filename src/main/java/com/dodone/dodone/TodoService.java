package com.dodone.dodone;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return todoRepository.findById(id).orElse(null);
    }
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }







}
