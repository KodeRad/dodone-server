package com.dodone.dodone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: INTERFACE EXTENDS
@Repository
public class TodoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // TODO: CHECK WHAT BEANPROPERTY DOES
    public List<Todo> getAll() {
        return jdbcTemplate.query("SELECT * FROM todo",
                BeanPropertyRowMapper.newInstance(Todo.class));
    }

    public Todo getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM todo " +
                "WHERE id = ?", BeanPropertyRowMapper.newInstance(Todo.class), id);
    }


    public int saveTodo(Todo todo) {
        jdbcTemplate.update("INSERT INTO todo(id, name,rating, priority, done) VALUES (?,?,?,?,?)",
                todo.getId(), todo.getName(), todo.getRating(), todo.isPriority(), todo.isDone());
        return 1;
    }

    public int saveManyTodo(List<Todo> todos) {

        todos.forEach(todo ->
                jdbcTemplate.update("INSERT INTO  todo(name, rating, priority, done) VALUES (?,?,?,?)",
                        todo.getName(), todo.getRating(), todo.isPriority(), todo.isDone()));

        return 1;
    }

    public int update(Todo todo) {
        return jdbcTemplate.update("UPDATE todo SET name=?, rating=?, priority=?, done=? WHERE id=?",
                todo.getName(), todo.getRating(), todo.isPriority(), todo.isDone(), todo.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM todo WHERE id=?", id);
    }
}
