package todo.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialDTO {
    private String login;
    private String password;
}
