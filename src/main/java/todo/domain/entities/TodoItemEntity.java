package todo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import todo.enums.Status;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "todoItens")
public class TodoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private Integer peso;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "todos_id")
    private TodoEntity todo;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
