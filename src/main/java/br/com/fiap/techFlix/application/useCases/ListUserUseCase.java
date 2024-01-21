package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.UserGateway;
import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.infrastructure.controllers.UserViewDTO;

public class ListUserUseCase {

    private final UserGateway userGateway;

    public ListUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public PagePort<UserViewDTO> findAll(int page, int size) {
        PagePort<User> users = userGateway.findAll(page, size);

        return users.map(UserViewDTO::new);
    }

    public UserViewDTO findById(String id) {
        User user = userGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new UserViewDTO(user);
    }
}
