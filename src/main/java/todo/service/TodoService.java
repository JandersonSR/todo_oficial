package todo.service;

import todo.domain.entities.TodoEntity;
import todo.DTOs.TodoDTO;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    TodoEntity create(TodoDTO dto);

    TodoEntity updateOne(TodoDTO todo);

    void deleteById(Long id);

    Optional<TodoEntity> findById(Long id);

    List<TodoEntity> list() ;
}
