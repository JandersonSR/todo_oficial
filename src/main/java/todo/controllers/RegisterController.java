package todo.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import todo.DTOs.CredentialDTO;
import todo.domains.entities.UserEntity;
import todo.services.UserService;

@RestController
@RequestMapping("/unauth/register")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public UserEntity register(@RequestBody @Valid CredentialDTO dto) {
            return this.userService.register(dto);
        }
}
