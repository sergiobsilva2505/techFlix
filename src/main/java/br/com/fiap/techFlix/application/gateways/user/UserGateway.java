package br.com.fiap.techFlix.application.gateways.user;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.domain.entities.user.User;

import java.util.Optional;

public interface UserGateway {

    User save(User user);

    PagePort<User> findAll(int page, int size);

    Optional<User> findById(String id);
}
