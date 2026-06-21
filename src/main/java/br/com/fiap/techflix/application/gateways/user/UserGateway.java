package br.com.fiap.techflix.application.gateways.user;

import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.ports.UserCreatePort;
import br.com.fiap.techflix.domain.entities.user.User;

import java.util.Optional;

public interface UserGateway {

    User save(UserCreatePort userCreatePort, String token);

    PagePort<User> findAll(int page, int size);

    Optional<User> findById(String id);

    boolean existsByEmail(String email);
}
