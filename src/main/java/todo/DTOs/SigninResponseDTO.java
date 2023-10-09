package todo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import todo.domains.entities.UserEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponseDTO {
    private String token;
    private UserEntity user;
}
