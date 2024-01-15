package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.useCases.CreateUserUseCase;
import br.com.fiap.techFlix.domain.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User user = createUserUseCase.createUser(userCreateDTO);

        URI uri = URI.create("/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }
}
