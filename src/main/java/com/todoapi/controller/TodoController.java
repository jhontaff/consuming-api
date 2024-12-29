package com.todoapi.controller;

import com.todoapi.dto.TodoDto;
import com.todoapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TodoDto>> getAll() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
        TodoDto todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoDto>> getTodosByUserId(@PathVariable Long userId) {
        List<TodoDto> todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);
    }

}
