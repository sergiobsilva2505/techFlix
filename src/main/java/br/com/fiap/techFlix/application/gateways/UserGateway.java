package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.User;

public interface UserGateway {

    User save(User user);

    PagePort<User> findAll(int page, int size);

    User findById(String id);
}
