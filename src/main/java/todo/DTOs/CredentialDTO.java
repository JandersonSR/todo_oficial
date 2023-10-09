package todo.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
public class CredentialDTO {

    private String level;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "campo.cpf.obrigatorio")
    @CPF(message = "campo.cpf.invalido")
    private String cpf;

    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Email(message = "{campo.login.invalido}")
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;

}
