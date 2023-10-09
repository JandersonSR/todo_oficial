package todo.services.imp;

import todo.domains.entities.TodoEntity;
import todo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.domains.entities.UserEntity;
import todo.domains.repository.UserRepository;
import todo.domains.repository.TodoRepository;
import todo.exceptions.BusinessRuleException;
import todo.DTOs.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoItemServiceImp implements TodoService {

    private final TodoRepository repository;
    private final UserRepository userRepository;

//    @Autowired
//    public TodoServiceImp(TodoRepository repository) {
//        this.repository = repository;
//    }

    public void validate (TodoDTO todo) {

    }

//    public Todo create (TodoDTO todo) {
//        validate(todo);
//        return this.repository.save(todo);
//    }

//    public Todo updateOne (TodoDTO todo) {
//        validate(todo);
//        return this.repository.save(todo);
//    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
    public Optional<TodoEntity> findById (Long id) {
        return this.repository.findById(id);
    }
    public List<TodoEntity> list () {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public TodoEntity create(TodoDTO dto) {
        Long userId = dto.getUser();
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessRuleException("Código de usuário inválido."));

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setCreatedAt(LocalDate.now());
        todoEntity.setTitle(dto.getTitle());
        todoEntity.setDeadLine(dto.getDeadLine());
        todoEntity.setUser(userEntity);

        return null;
    }


    @Override
    public TodoEntity updateOne(TodoDTO todo) {
        return null;
    }
}
