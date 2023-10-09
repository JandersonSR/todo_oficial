package todo.domains.entities;

import jakarta.persistence.*;
import todo.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private Integer peso;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "dead_line")
    private LocalDate deadLine;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @OneToMany(mappedBy = "todo")
    private List<TodoItemEntity> todoItens;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
