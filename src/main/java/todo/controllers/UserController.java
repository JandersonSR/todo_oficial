package todo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import todo.domains.entities.UserEntity;
import todo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

        @PatchMapping("{id}")
        public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody UserEntity user) {
            System.out.println("Passing here 2");
            Optional<UserEntity> selectedUser = this.userService.findById(id);
            if (selectedUser.isPresent()) {
                return ResponseEntity.ok(this.userService.updateOne(user));
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("{id}")
        public ResponseEntity delete(@PathVariable Long id) {
            System.out.println("Passing here 3");
            Optional<UserEntity> selectedUser = this.userService.findById(id);
            if (selectedUser.isPresent()) {
                this.userService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }

        @GetMapping("{id}")
        @ResponseStatus(HttpStatus.OK)
        @PreAuthorize("hasRole('ADMIN')")
        public UserEntity findById(@PathVariable Long id) {
            System.out.println("Passing here 4");
           return this.userService
                   .findById(id)
                   .orElseThrow(() ->
                           new ResponseStatusException(
                                   HttpStatus.NOT_FOUND,
                                   "Usuario não encontrado"
                           )
                   );
        }
        @GetMapping
        public List<UserEntity> list(UserEntity filter) {
            System.out.println("Passing here 5");
            ExampleMatcher matcher = ExampleMatcher
                    .matching()
                    .withIgnoreCase()
                    .withStringMatcher(
                            ExampleMatcher.StringMatcher.CONTAINING );

            Example example = Example.of(filter, matcher);
            return this.userService.list(example);
        }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fullUpdate( @PathVariable Long id,
                        @RequestBody UserEntity users ){
        this.userService
                .findById(id)
                .map( usuarioExistente -> {
                    users.setId(usuarioExistente.getId());
                    this.userService.createForTest(users);
                    return usuarioExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado") );
    }
}
