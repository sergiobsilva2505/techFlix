package br.com.fiap.techFlix.adapter.web.user;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.user.CreateUserUseCase;
import br.com.fiap.techFlix.application.useCases.user.ListUserUseCase;
import br.com.fiap.techFlix.domain.entities.user.User;
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
    public ResponseEntity<PagePort<UserViewDTO>> listUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PagePort<UserViewDTO> usersDTO = listUserUseCase.findAll(page, size).map(UserMapper::toView);

        return ResponseEntity.ok(usersDTO);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserViewDTO> showUser(@PathVariable("id") String id) {
        UserViewDTO userViewDTO = UserMapper.toView(listUserUseCase.findById(id));

        return ResponseEntity.ok(userViewDTO);
    }

}
