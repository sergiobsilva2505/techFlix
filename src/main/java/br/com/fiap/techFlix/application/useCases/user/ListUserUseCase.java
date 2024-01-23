package br.com.fiap.techFlix.application.useCases.user;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.adapter.web.user.UserViewDTO;

public class ListUserUseCase {

    private final UserGateway userGateway;

    public ListUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public PagePort<User> findAll(int page, int size) {
        return userGateway.findAll(page, size);
    }

    public User findById(String id) {
        return userGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
