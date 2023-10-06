package todo.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import todo.validation.NotEmptyList;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long id;

    @NotNull(message =  "{campo.descricao.obrigatorio}")
    private String description;

    private String title;
    private boolean isFinished;
    private Integer peso;
    private LocalDate deadLine;
    private Long user;

    @NotEmptyList(message = "{campo.items-todo.obrigatorio}")
    private List<TodoItemDTO> item;

}
