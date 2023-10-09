package todo.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import todo.DTOs.CredentialDTO;
import todo.DTOs.SigninDTO;
import todo.domains.entities.UserEntity;
import todo.domains.repository.UserRepository;
import org.springframework.data.domain.Example;
import todo.enums.Levels;
import todo.exceptions.BusinessRuleException;
import todo.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder  bCryptPasswordEncoder;

    public void validate (UserEntity user) {

    }

    public UserEntity register (CredentialDTO dto) {
        String userEmail = dto.getLogin();
        Optional<UserEntity> foundUser = this.findByEmail(userEmail);
        if (foundUser.isPresent()) {
            throw  new BusinessRuleException("Usuário existente.");
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(dto.getPassword());
        System.out.println("passwordEncodedpasswordEncoded" + passwordEncoded);

        UserEntity newUser = new UserEntity();

        newUser.setCreatedAt(LocalDate.now());
        newUser.setNome(dto.getNome());
        newUser.setCpf(dto.getCpf());
        newUser.setLevel(String.valueOf(Levels.valueOf(dto.getLevel())));
        newUser.setEmail(userEmail);
        newUser.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + newUser);
        return repository.save(newUser);
    }

    public UserEntity createForTest (UserEntity user) {
        validate(user);

        String userEmail = user.getEmail();
        Optional<UserEntity> foundUser = this.findByEmail(userEmail);
        if (foundUser.isPresent()) {
            throw  new BusinessRuleException("Usuário existente.");
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println("passwordEncodedpasswordEncoded" + passwordEncoded);
        user.setPassword(passwordEncoded);

        return repository.save(user);
    }

    public UserEntity updateOne (UserEntity user) {
        validate(user);
        return repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public Optional<UserEntity> findById (Long id) {
        return repository.findById(id);
    }

    public Optional<UserEntity> findByEmail (String email) {
        return repository.findOneByEmail(email);
    }
    public List<UserEntity> list (Example filter) {
        return repository.findAll(filter);
    }

    @Override
    public UserDetails authenticate(SigninDTO dto) {
        return null;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("joao@mail.com")) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return User
                .builder()
                .username("joao@mail.com")
                .password(bCryptPasswordEncoder.encode(("123")))
                .roles("USER", "ADMIN")
                .build();
    }

    public UserDetails loadUserByEmail(String email) throws BusinessRuleException {
        UserEntity user = repository.findOneByEmail(email).orElseThrow(() -> new BusinessRuleException("Usuário não encontrado"));

        return User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getLevel()))
                .build();
    }
}
