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

        Date timeNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(timeNow);
//        System.out.println(formattedDate);
        // DESIRED PATTERN FROM FRONTEND / YYYY-MM-DD HH:mm:ss

        List<Todo> todos = todoRepository.findAll();
        for (Todo todo : todos) {
            String time = todo.getDueDate();
//            System.out.println(time);


            // TODO: AN HOUR BEFORE
            if (todo.getDueDate() != null && time.equals(formattedDate)) {
                // TODO: PASS PROPS TO THE .sendMail METHOD TO GET THIS PARTICULAR TODO
                EmailService.sendMail("konrad.krasocki@smartbear.com", todo.getName());
            }
        }
    }

}
