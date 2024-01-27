package br.com.fiap.techFlix.application.gateways.user;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.ports.UserCreatePort;
import br.com.fiap.techFlix.domain.entities.user.User;

import java.util.Optional;

public interface UserGateway {

    User save(UserCreatePort userCreatePort, String token);

    PagePort<User> findAll(int page, int size);

    Optional<User> findById(String id);

    boolean existsByEmail(String email);
}
