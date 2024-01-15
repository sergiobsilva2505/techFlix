package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.UserGateway;
import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.infrastructure.persistence.UserRepository;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserRepositoryGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomain(userRepository.save(UserMapper.toPersistence(user)));
    }
}
