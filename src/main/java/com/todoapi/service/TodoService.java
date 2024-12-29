package com.todoapi.service;


import com.todoapi.dto.TodoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

    @Value("${todo.api.url}")
    private String todoApiUrl;
    private final RestTemplate restTemplate;

    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TodoDto> getAllTodos() {
        TodoDto[] todos = restTemplate.getForObject(todoApiUrl, TodoDto[].class);
        return Arrays.asList(todos);
    }

    public TodoDto getTodoById(Long id) {
        return restTemplate.getForObject(todoApiUrl + "/" + id, TodoDto.class, 1L);
    }

    public List<TodoDto> getTodosByUserId(Long userId) {
        List<TodoDto> todos = getAllTodos();
        return todos.stream().filter(
                todo -> todo.getUserId().equals(userId))
                .toList();
    }

}
