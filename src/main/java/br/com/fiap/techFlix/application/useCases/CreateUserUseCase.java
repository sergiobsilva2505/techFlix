package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.UserGateway;
import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.infrastructure.controllers.UserCreateDTO;
import br.com.fiap.techFlix.infrastructure.gateways.UserMapper;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(UserCreateDTO userCreateDTO) {
        UUID uuid = UUID.randomUUID();
        User user = UserMapper.toDomain(userCreateDTO);
        user.setToken(uuid.toString());

        return userGateway.save(user);
    }
}
