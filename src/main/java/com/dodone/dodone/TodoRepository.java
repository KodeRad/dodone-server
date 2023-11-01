package com.dodone.dodone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Todo> getAll() {
        return jdbcTemplate.query("SELECT id, name, rating FROM todo",
                BeanPropertyRowMapper.newInstance(Todo.class));
    }

    public Todo getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, rating FROM todo " +
                "WHERE id = ?", BeanPropertyRowMapper.newInstance(Todo.class), id);
    }


    // TODO: change it from list to a single json object
    public int saveTodo(List<Todo> todos) {

        todos.forEach(todo ->
                jdbcTemplate.update("INSERT INTO  todo(name, rating) VALUES (?, ?)",
                        todo.getName(), todo.getRating()));

        return 0;
    }

    public int update(Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET name=?, rating=? WHERE id=?",
                todo.getName(), todo.getRating(), todo.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM todo WHERE id=?", id);
    }
}
