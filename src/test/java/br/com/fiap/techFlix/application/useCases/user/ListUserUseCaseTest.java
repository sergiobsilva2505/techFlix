package br.com.fiap.techFlix.application.useCases.user;

import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListUserUseCaseTest {

    private UserGateway userGateway;
    private ListUserUseCase listUserUseCase;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        listUserUseCase = new ListUserUseCase(userGateway);
    }

    @Test
    void shouldReturnUsersWhenUsersExist() {
        User user = mock(User.class);
        PagePort pagePort = mock(PagePort.class);
        when(pagePort.getContent()).thenReturn(List.of(user));
        when(userGateway.findAll(0, 10)).thenReturn(pagePort);

        PagePort<User> result = listUserUseCase.findAll(0, 10);

        assertNotNull(result);
        assertEquals(user, result.getContent().getFirst());
    }

    @Test
    void shouldReturnUserWhenIdExists() {
        User user = mock(User.class);
        String id = "existingId";
        when(userGateway.findById(id)).thenReturn(Optional.of(user));

        User result = listUserUseCase.findById(id);

        assertEquals(user, result);
        verify(userGateway, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        String id = "nonExistingId";
        when(userGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> listUserUseCase.findById(id));
    }
}