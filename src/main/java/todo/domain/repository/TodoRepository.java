package todo.domain.repository;

import todo.domain.entities.TodoEntity;
import todo.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<TodoEntity,Long> {
    List<TodoEntity> findByUser(UserEntity userEntity);
}
