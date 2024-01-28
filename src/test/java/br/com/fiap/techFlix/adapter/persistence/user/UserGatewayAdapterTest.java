package br.com.fiap.techFlix.adapter.persistence.user;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.ports.UserCreatePort;
import br.com.fiap.techFlix.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserGatewayAdapterTest {

    private UserRepository userRepository;
    private UserGatewayAdapter userGatewayAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userGatewayAdapter = new UserGatewayAdapter(userRepository);
    }

    @Test
    void shouldSaveUser() {
        UserCreatePort userCreatePort = mock(UserCreatePort.class);
        UserDocument user = new UserDocument("id", "name", "email", "password", "token");
        when(userRepository.save(any())).thenReturn(user);

        User result = userGatewayAdapter.save(userCreatePort, "token");

        assertNotNull(result);
        verify(userRepository).save(any());
    }

    @Test
    void shouldFindAllUsers() {
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        PagePort<User> result = userGatewayAdapter.findAll(0, 10);

        assertNotNull(result);
        verify(userRepository).findAll(any(PageRequest.class));
    }

    @Test
    void shouldFindUserById() {
        UserDocument user = new UserDocument("id", "name", "email", "password", "token");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

        Optional<User> result = userGatewayAdapter.findById("id");

        assertTrue(result.isPresent());
        verify(userRepository).findById(anyString());
    }

    @Test
    void shouldNotFindUserById() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        Optional<User> result = userGatewayAdapter.findById("id");

        assertFalse(result.isPresent());
        verify(userRepository).findById(anyString());
    }

    @Test
    void shouldExistByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        boolean result = userGatewayAdapter.existsByEmail("email");

        assertTrue(result);
        verify(userRepository).existsByEmail(anyString());
    }

    @Test
    void shouldNotExistByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        boolean result = userGatewayAdapter.existsByEmail("email");

        assertFalse(result);
        verify(userRepository).existsByEmail(anyString());
    }
}