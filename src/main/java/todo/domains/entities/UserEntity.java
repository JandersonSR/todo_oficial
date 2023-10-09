package todo.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "campo.cpf.obrigatorio")
    @CPF(message = "campo.cpf.invalido")
    private String cpf;

    @Column(name = "email", length = 100)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    @Email(message = "{campo.email.invalido}")
    private String email;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
    private String level;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<TodoEntity> todo;

    public UserEntity(String nome) {
        this.setNome(nome);
    }


    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = LocalDate.now();
    }

}
