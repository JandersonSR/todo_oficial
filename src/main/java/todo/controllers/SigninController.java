package todo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import todo.DTOs.SigninDTO;
import todo.DTOs.SigninResponseDTO;
import todo.configs.jwt.JwtService;
import todo.domains.entities.UserEntity;
import todo.services.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/unauth/login")
public class SigninController {

    private final UserService userService;
    private final JwtService jwtService;

        @PostMapping
        @ResponseStatus(HttpStatus.FOUND)
        public SigninResponseDTO signin(@RequestBody @Valid SigninDTO dto) {
            userService.authenticate(dto);
            SigninResponseDTO responseDTO = new SigninResponseDTO();
            UserEntity user = UserEntity
                    .builder()
                    .email(dto.getLogin())
                    .password(dto.getPassword())
                    .build();

            String token = jwtService.getToken(user);
            responseDTO.setUser(user);
            responseDTO.setToken(token);

            return responseDTO;
        }
}
