package br.com.fiap.techFlix.adapter.web.user;

import br.com.fiap.techFlix.application.ports.UserCreatePort;

public record UserCreateDTO(String name, String email, String password) implements UserCreatePort {
}
