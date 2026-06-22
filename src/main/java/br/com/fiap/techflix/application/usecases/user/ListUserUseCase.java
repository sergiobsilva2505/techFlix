package br.com.fiap.techflix.application.usecases.user;

import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.gateways.user.UserGateway;
import br.com.fiap.techflix.domain.entities.user.User;

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
