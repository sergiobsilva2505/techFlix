package br.com.fiap.techFlix.adapter.web.user;

import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.user.CreateUserUseCase;
import br.com.fiap.techFlix.application.useCases.user.ListUserUseCase;
import br.com.fiap.techFlix.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    CreateUserUseCase createUserUseCase;

    @Mock
    ListUserUseCase listUserUseCase;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUser() {
        User user = mock(User.class);
        when(user.getId()).thenReturn("id");
        when(createUserUseCase.createUser(any(UserCreateDTO.class))).thenReturn(user);

        ResponseEntity<String> response = userController.createUser(mock(UserCreateDTO.class));

        assertEquals(201, response.getStatusCodeValue());
        verify(createUserUseCase, times(1)).createUser(any(UserCreateDTO.class));
    }

    @Test
    void shouldListUsers() {
        PagePort<User> pagePort = new PageDTO<>(Page.empty());
        when(listUserUseCase.findAll(anyInt(), anyInt())).thenReturn(pagePort);

        ResponseEntity<PagePort<UserViewDTO>> response = userController.listUser(0, 10);

        assertEquals(200, response.getStatusCodeValue());
        verify(listUserUseCase, times(1)).findAll(anyInt(), anyInt());
    }

    @Test
    void shouldShowUser() {
        User user = mock(User.class);
        when(listUserUseCase.findById(anyString())).thenReturn(user);

        ResponseEntity<UserViewDTO> response = userController.showUser("id");

        assertEquals(200, response.getStatusCodeValue());
        verify(listUserUseCase, times(1)).findById(anyString());
    }
}