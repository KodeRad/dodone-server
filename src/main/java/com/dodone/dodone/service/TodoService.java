package com.dodone.dodone.service;

import com.dodone.dodone.controller.errors.ExceptionNoSuchElement;
import com.dodone.dodone.entity.Todo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.dodone.dodone.repository.TodoRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {


    private final TodoRepository
            todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getByID(Long id)  {
        return todoRepository.findById(id).
                orElseThrow(ExceptionNoSuchElement::new);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void delete(Long id) {

        // EXTRA QUERY FOR THROWING AN ERROR
        todoRepository.findById(id).
                orElseThrow(ExceptionNoSuchElement::new);

        todoRepository.deleteById(id);
    }


}
