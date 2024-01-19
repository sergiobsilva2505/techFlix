package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.useCases.CreateUserUseCase;
import br.com.fiap.techFlix.application.useCases.ListUserUseCase;
import br.com.fiap.techFlix.domain.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUserUseCase listUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, ListUserUseCase listUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUserUseCase = listUserUseCase;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User user = createUserUseCase.createUser(userCreateDTO);

        URI uri = URI.create("/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/users")
    public ResponseEntity<PagePort<UserViewDTO>> listUser(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        PagePort<UserViewDTO> usersDTO = listUserUseCase.findAll(page, size);

        return ResponseEntity.ok(usersDTO);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserViewDTO> showUser(@PathVariable("id") String id) {
        UserViewDTO userViewDTO = listUserUseCase.findById(id);

        return ResponseEntity.ok(userViewDTO);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserViewDTO> updateUser(@PathVariable("id") String id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserViewDTO userViewDTO = listUserUseCase.findById(id);

        return ResponseEntity.ok(userViewDTO);
    }
}
