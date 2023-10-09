package todo.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.domains.entities.TodoEntity;
import todo.domains.entities.UserEntity;
import todo.domains.entities.TodoItemEntity;
import todo.domains.repository.TodoItemRepository;
import todo.domains.repository.TodoRepository;
import todo.domains.repository.UserRepository;
import todo.enums.Status;
import todo.exceptions.BusinessRuleException;
import todo.DTOs.TodoDTO;
import todo.DTOs.TodoItemDTO;
import todo.services.TodoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class TodoServiceImp implements TodoService {

    private final TodoRepository repository;
    private final UserRepository userRepository;
    private final TodoItemRepository todoItemRepository;

    public void validate (TodoEntity todoEntity) {

    }

    public TodoEntity updateOne (TodoEntity todoEntity) {
        validate(todoEntity);
        return this.repository.save(todoEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
    @Override
    public Optional<TodoEntity> findById (Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<TodoEntity> list () {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public TodoEntity create(TodoDTO dto) {
        Long userId = dto.getUser();

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new BusinessRuleException("Código de cliente inválido."));

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setCreatedAt(LocalDate.now());
        todoEntity.setTitle(dto.getTitle());
        todoEntity.setDeadLine(dto.getDeadLine());
        todoEntity.setUser(userEntity);
        todoEntity.setStatus(Status.PENDENTE);

        List<TodoItemEntity> todoItens = converterItens(todoEntity, dto.getItem());
        repository.save(todoEntity);
        todoItemRepository.saveAll(todoItens);
        todoEntity.setTodoItens(todoItens);
        return todoEntity;
    }

    @Override
    public TodoEntity updateOne(TodoDTO todo) {
        return null;
    }


    private List<TodoItemEntity> converterItens(TodoEntity todoEntity, List<TodoItemDTO> todoItems){
        if(todoItems.isEmpty()){
            throw new BusinessRuleException("Não é possível criar um todo sem items.");
        }

        return todoItems
                .stream()
                .map( dto -> {

                    TodoItemEntity todoItemEntity = new TodoItemEntity();
                    todoItemEntity.setPeso(dto.getPeso());
                    todoItemEntity.setTodo(todoEntity);

                    return todoItemEntity;
                }).collect(Collectors.toList());

    }
}
