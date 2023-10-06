package todo.service;

import todo.domain.entities.TodoEntity;
import todo.DTOs.TodoItemDTO;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {
    TodoEntity create(TodoItemDTO dto);

    TodoEntity updateOne(TodoItemDTO todo);

    void deleteById(Long id);

    Optional<TodoEntity> findById(Long id);

    List<TodoEntity> list() ;
}
