package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.User;

public interface UserGateway {

    User save(User user);
}
