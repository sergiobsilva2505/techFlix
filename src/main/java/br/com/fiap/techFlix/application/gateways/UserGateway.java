package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.User;

import java.util.Optional;

public interface UserGateway {

    User save(User user);

    PagePort<User> findAll(int page, int size);

    Optional<User> findById(String id);
}
