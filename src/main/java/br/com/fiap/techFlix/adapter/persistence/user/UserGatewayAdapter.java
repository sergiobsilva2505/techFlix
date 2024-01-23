package br.com.fiap.techFlix.adapter.persistence.user;

import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.adapter.web.user.UserMapper;
import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.ports.UserCreatePort;
import br.com.fiap.techFlix.domain.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class UserGatewayAdapter implements UserGateway {

    private final UserRepository userRepository;

    public UserGatewayAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserCreatePort userCreatePort, String token) {
        return UserMapper.toDomain(userRepository.save(UserMapper.toPersistence(userCreatePort, token)));
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
