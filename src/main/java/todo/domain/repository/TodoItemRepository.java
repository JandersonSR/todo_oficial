package todo.domain.repository;

import todo.domain.entities.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoItemRepository extends JpaRepository<TodoItemEntity,Long> {
}
