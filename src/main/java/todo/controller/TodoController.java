package todo.controller;

import todo.domain.entities.TodoEntity;
import todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import todo.DTOs.TodoDTO;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Long create(@RequestBody @Valid TodoDTO dto) {
        System.out.println("Passing here ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        TodoEntity todoEntity = todoService.create(dto);
        return todoEntity.getId();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoEntity> update(@PathVariable Long id, @RequestBody TodoDTO todo) {
        Optional<TodoEntity> selectedTodo = todoService.findById(id);
        if (selectedTodo.isPresent()) {
            return ResponseEntity.ok(todoService.updateOne(todo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<TodoEntity> selectedTodo = todoService.findById(id);
        if (selectedTodo.isPresent()) {
            todoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public TodoEntity findById(@PathVariable Long id) {
        return todoService
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuário não encontrado!"
                        )
                );
    }
    @GetMapping
    public ResponseEntity<List<TodoEntity>> list() {
        System.out.println("Passing here");
        return ResponseEntity.ok(todoService.list());
    }
}
