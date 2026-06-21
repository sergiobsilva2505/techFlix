package br.com.fiap.techflix.domain.entities.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test @DisplayName("Cria usuário com parâmetros válidos")
    void shouldCreateUserWithValidParameters() {
        User user = new User("1", "Lucas", "lucas@gmail.com", "password", "token");

        assertEquals("1", user.getId());
        assertEquals("Lucas", user.getName());
        assertEquals("lucas@gmail.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("token", user.getToken());
    }

    @Test @DisplayName("Lança exceção quando ID é nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "Lucas", "lucas@gmail.com", "password", "token"));
    }

    @Test @DisplayName("Lança exceção quando nome é nulo")
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", null, "lucas@gmail.com", "password", "token"));
    }

    @Test @DisplayName("Lança exceção quando e-mail é nulo")
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", null, "password", "token"));
    }

    @Test @DisplayName("Lança exceção quando senha é nula")
    void shouldThrowExceptionWhenPasswordIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", null, "token"));
    }

    @Test @DisplayName("Lança exceção quando token é nulo")
    void shouldThrowExceptionWhenTokenIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", "password", null));
    }

    @Test @DisplayName("Lança exceção quando ID é vazio")
    void shouldThrowExceptionWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "Lucas", "lucas@gmail.com", "password", "token"));
    }

    @Test @DisplayName("Lança exceção quando nome é vazio")
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "", "lucas@gmail.com", "password", "token"));
    }

    @Test @DisplayName("Lança exceção quando e-mail é vazio")
    void shouldThrowExceptionWhenEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "", "password", "token"));
    }

    @Test @DisplayName("Lança exceção quando senha é vazia")
    void shouldThrowExceptionWhenPasswordIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", "", "token"));
    }

    @Test @DisplayName("Lança exceção quando token é vazio")
    void shouldThrowExceptionWhenTokenIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new User("1", "Lucas", "lucas@gmail.com", "password", ""));
    }
}