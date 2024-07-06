package com.ansitindia.taskmgt.service;

import com.ansitindia.taskmgt.exception.TodoCollectionException;
import com.ansitindia.taskmgt.model.TodoDTO;
import java.util.List;

import javax.validation.ConstraintViolationException;

public interface TodoService {
    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;

    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id)throws TodoCollectionException;

    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;

    public void deleteTodoById(String id)throws TodoCollectionException;


}
