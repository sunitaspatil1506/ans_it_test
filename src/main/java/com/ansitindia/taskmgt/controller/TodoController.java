package com.ansitindia.taskmgt.controller;

import com.ansitindia.taskmgt.exception.TodoCollectionException;
import com.ansitindia.taskmgt.model.TodoDTO;
import com.ansitindia.taskmgt.repository.TodoRepository;
import com.ansitindia.taskmgt.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> todos =todoService.getAllTodos();
        return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        try{
            todoService.createTodo(todo);
            return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") Long id){
        try{
           return new ResponseEntity<>(todoService.getSingleTodo(id),HttpStatus.OK) ;
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody TodoDTO todo){
        try{
            todoService.updateTodo(id, todo);
            return  new ResponseEntity<>("Update todo with  id "+id, HttpStatus.OK);
        }catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        try{
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted with id "+id, HttpStatus.OK);
        }catch(TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
