package br.com.fiap.techFlix.application.useCases.user;

import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.ports.UserCreatePort;
import br.com.fiap.techFlix.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    private UserGateway userGateway;
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        createUserUseCase = new CreateUserUseCase(userGateway);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        UserCreatePort userCreatePort = mock(UserCreatePort.class);
        User user = mock(User.class);
        when(userCreatePort.email()).thenReturn("test@test.com");
        when(userGateway.existsByEmail(anyString())).thenReturn(false);
        when(userGateway.save(eq(userCreatePort), anyString())).thenReturn(user);

        User result = createUserUseCase.createUser(userCreatePort);

        assertEquals(user, result);
        verify(userGateway).save(eq(userCreatePort), anyString());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        UserCreatePort userCreatePort = mock(UserCreatePort.class);
        when(userCreatePort.email()).thenReturn("test@test.com");
        when(userGateway.existsByEmail(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> createUserUseCase.createUser(userCreatePort));
        verify(userGateway, never()).save(eq(userCreatePort), anyString());
    }
}