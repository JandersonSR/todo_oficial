package todo.service;

import org.springframework.data.domain.Example;
import todo.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity create(UserEntity userEntity);
    UserEntity updateOne (UserEntity userEntity);

    void deleteById(Long id);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> list(Example filter);
}
