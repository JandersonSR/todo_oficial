package todo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.domain.entities.UserEntity;
import todo.domain.repository.UserRepository;
import org.springframework.data.domain.Example;
import todo.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    public void validate (UserEntity user) {

    }

    public UserEntity create (UserEntity user) {
        validate(user);
        return this.repository.save(user);
    }

    public UserEntity updateOne (UserEntity user) {
        validate(user);
        return this.repository.save(user);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
    public Optional<UserEntity> findById (Long id) {
        return this.repository.findById(id);
    }
    public List<UserEntity> list (Example filter) {
        return this.repository.findAll(filter);
    }

}
