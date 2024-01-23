package br.com.fiap.techFlix.application.useCases.user;

import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.adapter.web.user.UserCreateDTO;
import br.com.fiap.techFlix.adapter.web.user.UserMapper;

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
