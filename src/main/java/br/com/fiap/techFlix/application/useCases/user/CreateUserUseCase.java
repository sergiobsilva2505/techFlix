package br.com.fiap.techFlix.application.useCases.user;

import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.ports.UserCreatePort;
import br.com.fiap.techFlix.domain.entities.user.User;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(UserCreatePort userCreatePort) {
        if (userGateway.existsByEmail(userCreatePort.email())) {
            throw new IllegalArgumentException("User already exists");
        }

        UUID uuid = UUID.randomUUID();
        return userGateway.save(userCreatePort, uuid.toString());
    }
}
