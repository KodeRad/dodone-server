package com.dodone.dodone.service;

import com.dodone.dodone.controller.errors.ExceptionNoSuchElement;
import com.dodone.dodone.entity.Todo;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.dodone.dodone.repository.TodoRepository;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        todoRepository.deleteById(id);
    }

    public void sendEmail() throws MessagingException {
//        Date timeNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DDTHH:mm:ss");
//        String formattedDate = sdf.format(timeNow);
//

// DESIRED PATTERN / YYYY-MM-DDTHH:mm:ss

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//        String formattedDateTime = timeNow.format(formatter);
//        System.out.println(formattedDate);


        List<Todo> todos = todoRepository.findAll();
        for (Todo todo : todos) {
            // ??
            String time = sdf.format(todo.getDueDate());
            if (todo.getDueDate() != null && time.equals("formattedDate")) {
                EmailService.sendMail("konrad.krasocki@smartbear.com");
            }
        }

        // JAVA TERMINAL
        // 2023-11-09T14:31:00.457+01:00

        // DUEDATE from JS
        // 2023-11-09T14:31:00.000Z
    }

}
