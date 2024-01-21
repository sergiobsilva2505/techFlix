package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.UserGateway;
import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.infrastructure.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserRepositoryGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomain(userRepository.save(UserMapper.toPersistence(user)));
    }

    @Override
    public PagePort<User> findAll(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size)).map(UserMapper::toDomain);

        return new PageDTO<>(users);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id).map(UserMapper::toDomain);
    }
}
