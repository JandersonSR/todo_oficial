package todo.domains.repository;

import todo.domains.entities.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoItemRepository extends JpaRepository<TodoItemEntity,Long> {
}
