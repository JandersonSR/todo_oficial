package todo.domains.repository;

import todo.domains.entities.TodoEntity;
import todo.domains.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<TodoEntity,Long> {
    List<TodoEntity> findByUser(UserEntity userEntity);
}
