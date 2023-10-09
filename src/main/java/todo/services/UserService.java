package todo.services;

import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import todo.DTOs.CredentialDTO;
import todo.DTOs.SigninDTO;
import todo.domains.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createForTest(UserEntity userEntity);

    UserEntity register(CredentialDTO dto);
    UserEntity updateOne (UserEntity userEntity);

    void deleteById(Long id);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> list(Example filter);

    UserDetails authenticate(SigninDTO dto);
}
